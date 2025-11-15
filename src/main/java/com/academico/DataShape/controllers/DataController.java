package com.academico.DataShape.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.academico.DataShape.model.dto.UploadResponseDTO;


@RestController
@RequestMapping("/data")
public class DataController {
    


    @GetMapping("upload")
    public ResponseEntity<UploadResponseDTO> getMethodName(@RequestParam MultipartFile csvFile) {
            // Fazer o service
            return ResponseEntity.ok(new UploadResponseDTO());
    }
    
}
