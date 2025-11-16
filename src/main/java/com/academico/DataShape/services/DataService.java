package com.academico.DataShape.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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

    public List<Obra> parseObras(MultipartFile fileCSV) {
        List<Obra> obras = new ArrayList<>();

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

                Obra obra = new Obra();
                obra.setTituloObra(titulo);

                obras.add(obra);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return obras;
    }

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
            venda.setMesVenda(r.get("Mês"));
            venda.setQuantidadeVenda(Integer.parseInt(r.get("Quantidade")));

            venda.setObra(findVariasByTitulo(r.get("Obra Vendida")));

            venda.setValorPago(parseValor(r.get("Valor Pago")));

            venda.setMetodoPagamento(
                MetodoPagamento.fromCsv(r.get("Forma de Pagamento"))
            );

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

    /*
     * Quando fizermos o ajuste de titulo unico
     * private Obra findObraByTitulo(String titulo) {
     * return obraRepository.findByTituloObra(titulo)
     * .orElseThrow(() -> new RuntimeException("Obra não encontrada: " + titulo));
     * }
     */

    private Obra findVariasByTitulo(String titulo) {
        List<Obra> obras = obraRepository.findAllByTituloObra(titulo);
        if (obras.isEmpty()) {
            throw new RuntimeException("Obra não encontrada: " + titulo);
        }

        return obras.get(0); // primeira obra encontrada
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

}
