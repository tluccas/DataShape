package com.academico.DataShape.repository;

import java.util.List;

import com.academico.DataShape.model.dto.responses.VendasMensaisDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.academico.DataShape.model.dto.responses.findAllVendasDTO;
import com.academico.DataShape.model.dto.responses.findTotalVendasResponse;
import com.academico.DataShape.model.entity.Venda;

public interface VendaRepository extends JpaRepository<Venda, Long> {

    @Query(""" 
        SELECT new com.academico.DataShape.model.dto.responses.findAllVendasDTO(
        o.tituloObra, v.dataVenda, v.valorPago)
        FROM Venda v
        JOIN v.obra o
                """)
    List<findAllVendasDTO> findAllVendas();

    @Query ("""
            SELECT new com.academico.DataShape.model.dto.responses.findTotalVendasResponse(
                o.tituloObra,
                SUM(v.valorPago)
                )
            FROM Venda v
            JOIN v.obra o
            GROUP BY o.tituloObra
            ORDER BY SUM(v.valorPago) DESC
            """)
    List<findTotalVendasResponse> findTotalVendas();

    @Query("""
            SELECT new com.academico.DataShape.model.dto.responses.VendasMensaisDTO(
                CAST(FUNCTION('YEAR', v.dataVenda) AS integer),
                CAST(FUNCTION('MONTHNAME', v.dataVenda) AS string),
                o.tituloObra,
                SUM(v.valorPago)
            )
            FROM Venda v
            JOIN v.obra o
            GROUP BY FUNCTION('YEAR', v.dataVenda), FUNCTION('MONTHNAME', v.dataVenda), o.tituloObra
            ORDER BY FUNCTION('MONTH', v.dataVenda) ASC, SUM(v.valorPago) DESC
            """)
    List<VendasMensaisDTO> findVendasPorMes();
}

