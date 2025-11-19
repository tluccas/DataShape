package com.academico.DataShape.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.Normalizer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.academico.DataShape.model.entity.MetodoPagamento;
import com.academico.DataShape.model.entity.Obra;
import com.academico.DataShape.model.entity.Venda;
import com.academico.DataShape.repository.ObraRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class DataService {

    @Autowired
    private ObraRepository obraRepository;

    @SuppressWarnings("deprecation")
    public List<Obra> parseObras(MultipartFile fileCSV) {
        List<Obra> obras = new ArrayList<>();
        Set<String> titulosNormalizados = new HashSet<>();

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(fileCSV.getInputStream(), StandardCharsets.ISO_8859_1))) {

            Iterable<CSVRecord> registros = CSVFormat.DEFAULT
                    .withDelimiter(';')
                    .withFirstRecordAsHeader()
                    .withTrim()
                    .parse(reader);

            for (CSVRecord r : registros) {

                String titulo = r.get("Obra Vendida");

                if (titulo == null || titulo.isBlank()) {
                    continue;
                }

                String n = normalizar(titulo);

          
                if (!titulosNormalizados.add(n)) { //Verifica se a obra ja existe com comparação normalizada
                    continue;
                }

                Obra obra = new Obra();
                obra.setTituloObra(titulo);
                obra.setTituloNormalizado(n);
                obras.add(obra);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return obras;
    }

    @SuppressWarnings("deprecation")
    public List<Venda> parseVendas(MultipartFile fileCSV) {
        List<Venda> vendas = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(fileCSV.getInputStream(), StandardCharsets.ISO_8859_1))) {

            Iterable<CSVRecord> registros = CSVFormat.DEFAULT
                    .withDelimiter(';')
                    .withFirstRecordAsHeader()
                    .withIgnoreSurroundingSpaces()
                    .withTrim(true)
                    .parse(reader);

            for (CSVRecord r : registros) {

                // Se alguma coluna essencial está vazia, pula
                if (r.get("Obra Vendida").isBlank()) {
                    continue;
                }

                Venda venda = new Venda();

                venda.setDataVenda(parseData(r.get("Data")));
                try {
                    venda.setQuantidadeVenda(Integer.parseInt(r.get("Quantidade")));
                } catch (NumberFormatException e) {
                    System.err.println("Aviso: quantidade inválida encontrada para a obra: " + r.get("Obra Vendida") + ". Definindo para 0.");
                    venda.setQuantidadeVenda(0);
                }

                try {
                    venda.setObra(findObraByTitulo(r.get("Obra Vendida")));
                } catch (RuntimeException e) {

                    System.err.println("ERRO CRÍTICO: Obra não encontrada no BD durante o parse de vendas: " + r.get("Obra Vendida"));
                    continue;
                }

                try{
                    venda.setValorPago(parseValor(r.get("Valor Pago")));
                }catch (NumberFormatException e) {
                    System.err.println("Aviso: valor inválido encontrado para a obra: " + r.get("Obra Vendida") + ". Definindo para 0.0");
                    venda.setValorPago(0.0);
                }

                venda.setMetodoPagamento(
                        MetodoPagamento.fromCsv(r.get("Forma de Pagamento")));

                vendas.add(venda);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return vendas;
    }

    private LocalDate parseData(String dataStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(dataStr, formatter);
    }

    
      private Obra findObraByTitulo(String titulo) {
        String n = normalizar(titulo);
        return obraRepository.findByTituloNormalizado(n)
            .orElseThrow(() -> new RuntimeException("Obra não encontrada: " + titulo));
      }

    // Normalizar valor vendido
    private Double parseValor(String valorStr) {
        if (valorStr == null || valorStr.isBlank())
            return 0.0;

        // Remove "R$", espaços e troca vírgula por ponto
        String numero = valorStr.replace("R$", "")
                .replace(".", "")
                .replace(",", ".")
                .trim();
        return Double.parseDouble(numero);
    }

    private String normalizar(String s) {
    return Normalizer.normalize(s, Normalizer.Form.NFD)
            .replaceAll("[^\\p{ASCII}]", "")  
            .replaceAll("\\s+", " ")            
            .trim()
            .toUpperCase();
}

}
