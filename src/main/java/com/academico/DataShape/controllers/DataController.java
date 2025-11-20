package com.academico.DataShape.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.academico.DataShape.model.dto.UploadResponseDTO;
import com.academico.DataShape.model.entity.Obra;
import com.academico.DataShape.model.entity.Venda;
import com.academico.DataShape.repository.ObraRepository;
import com.academico.DataShape.repository.VendaRepository;
import com.academico.DataShape.services.DataService;


@RestController
@RequestMapping("/data")
public class DataController {
    
    private DataService dataService;

    private ObraRepository obraRepository;

    private VendaRepository vendaRepository;

    public DataController(DataService dataService, ObraRepository obraRepository, VendaRepository vendaRepository) {
        this.dataService = dataService;
        this.obraRepository = obraRepository;
        this.vendaRepository = vendaRepository;
    }


    @PostMapping("upload")
    public ResponseEntity<UploadResponseDTO> getMethodName(@RequestParam MultipartFile csvFile) {
        List<Obra> obras = dataService.parseObras(csvFile);
        obraRepository.saveAll(obras);

        List<Venda> vendas = dataService.parseVendas(csvFile);
        vendaRepository.saveAll(vendas);
        
        return ResponseEntity.ok(new UploadResponseDTO(obras.size(), vendas.size()));
    }


    
    
}
