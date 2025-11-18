package com.academico.DataShape.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.academico.DataShape.model.dto.responses.findAllVendasDTO;
import com.academico.DataShape.model.entity.Venda;

public interface VendaRepository extends JpaRepository<Venda, Long> {

    @Query(""" 
        SELECT new com.academico.DataShape.model.dto.responses.findAllVendasDTO(
        o.tituloObra, v.dataVenda, v.valorPago)
        FROM Venda v
        JOIN v.obra o
                """)
    List<findAllVendasDTO> findAllVendas();
    
}
