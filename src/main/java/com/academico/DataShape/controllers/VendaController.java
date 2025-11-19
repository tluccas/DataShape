package com.academico.DataShape.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.academico.DataShape.model.dto.responses.findAllVendasResponse;
import com.academico.DataShape.model.dto.responses.findTotalVendasResponse;
import com.academico.DataShape.repository.VendaRepository;
import com.academico.DataShape.services.QueryService;

@RestController
@RequestMapping("/vendas")
public class VendaController {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private QueryService queryService;
    
    @GetMapping("all-vendas")
    public ResponseEntity<findAllVendasResponse> getMethodName() {
        return ResponseEntity.ok(queryService.getAllVendas());
    }

    @GetMapping("/total-vendas")
    public ResponseEntity<List<findTotalVendasResponse>> getTotalVendas() {
        return ResponseEntity.ok(vendaRepository.findTotalVendas());
    }
}
