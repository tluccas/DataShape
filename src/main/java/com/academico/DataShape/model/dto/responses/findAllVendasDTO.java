package com.academico.DataShape.model.dto.responses;

import java.time.LocalDate;

public record findAllVendasDTO(String obra, LocalDate data_venda, Double valor_pago ) {
    
}
