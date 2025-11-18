package com.academico.DataShape.model.dto.responses;

import java.util.List;

public record findAllVendasResponse(int total, List<findAllVendasDTO> vendas) {}
