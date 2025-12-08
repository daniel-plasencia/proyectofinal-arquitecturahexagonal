package com.danielplasencia.proyectofinal.arquitecturahexagonal.infrastructure.adapters.input.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransaccionRequest {

    private String cuentaOrigenId;
    private String cuentaDestinoId;
    private BigDecimal monto;
    private String tipo;         // opcional, en el usecase default "TRANSFERENCIA"
    private String descripcion;  // opcional

}
