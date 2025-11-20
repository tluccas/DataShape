package com.academico.DataShape.controllers;

import java.util.List;

import com.academico.DataShape.model.dto.responses.VendasMensaisDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.academico.DataShape.model.dto.responses.findAllVendasResponse;
import com.academico.DataShape.model.dto.responses.findQtdTotalVendasResponse;
import com.academico.DataShape.model.dto.responses.findTotalVendasResponse;
import com.academico.DataShape.repository.VendaRepository;
import com.academico.DataShape.services.QueryService;

@RestController
@RequestMapping("/vendas")
public class VendaController {

    private VendaRepository vendaRepository;

    private QueryService queryService;

    public VendaController(VendaRepository vendaRepository, QueryService queryService) {
        this.vendaRepository = vendaRepository;
        this.queryService = queryService;
    }
    
    @GetMapping("all-vendas")
    public ResponseEntity<findAllVendasResponse> getMethodName() {
        return ResponseEntity.ok(queryService.getAllVendas());
    }

    @GetMapping("/total-vendas")
    public ResponseEntity<List<findTotalVendasResponse>> getTotalVendas() {
        return ResponseEntity.ok(vendaRepository.findTotalVendas());
    }

    @GetMapping("/vendas-por-mes")
    public ResponseEntity<List<VendasMensaisDTO>> getVendasPorMes() {
        return ResponseEntity.ok(vendaRepository.findVendasPorMes());
    }

    @GetMapping("/qtd-total-vendas")
    public ResponseEntity<findQtdTotalVendasResponse> getQtdTotalVendas() {
        return ResponseEntity.ok(queryService.getQtdTotalVendasResponse());
    }
}
