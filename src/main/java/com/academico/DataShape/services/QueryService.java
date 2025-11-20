package com.academico.DataShape.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.academico.DataShape.model.dto.responses.QtdVendaDTO;
import com.academico.DataShape.model.dto.responses.findAllVendasDTO;
import com.academico.DataShape.model.dto.responses.findAllVendasResponse;
import com.academico.DataShape.model.dto.responses.findQtdTotalVendasResponse;
import com.academico.DataShape.repository.VendaRepository;

@Service
public class QueryService {
    @Autowired
    private VendaRepository vendaRepository;


    public findAllVendasResponse getAllVendas(){
        
        List<findAllVendasDTO> vendas = vendaRepository.findAllVendas();
        int total = vendas.size();

        return new findAllVendasResponse(total, vendas);
    }

    public findQtdTotalVendasResponse getQtdTotalVendasResponse(){

        List<QtdVendaDTO> qtdVendasPorObra = vendaRepository.findQtdVendasPorObra();
        Long totalUnidadesVendidas = qtdVendasPorObra.stream()
                .mapToLong(QtdVendaDTO::vendido)
                .sum();
            
        return new findQtdTotalVendasResponse(totalUnidadesVendidas, qtdVendasPorObra);
            
    }

    
}
