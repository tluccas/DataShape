package com.academico.DataShape.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.academico.DataShape.model.dto.responses.findAllVendasDTO;
import com.academico.DataShape.model.dto.responses.findAllVendasResponse;
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
    
}
