package com.academico.DataShape.model.dto.responses;
import java.util.List;
public record findQtdTotalVendasResponse(Long total_un_vendidas, List<QtdVendaDTO> qtd_vendas_por_obra) {
    
}
