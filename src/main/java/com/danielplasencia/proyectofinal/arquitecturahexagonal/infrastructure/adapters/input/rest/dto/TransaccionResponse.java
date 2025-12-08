package com.danielplasencia.proyectofinal.arquitecturahexagonal.infrastructure.adapters.input.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransaccionResponse {

    private String id;
    private String cuentaOrigenId;
    private String cuentaDestinoId;
    private BigDecimal monto;
    private BigDecimal comision;
    private String tipo;
    private String estado;
    private String descripcion;
    private LocalDateTime fechaCreacion;

}
