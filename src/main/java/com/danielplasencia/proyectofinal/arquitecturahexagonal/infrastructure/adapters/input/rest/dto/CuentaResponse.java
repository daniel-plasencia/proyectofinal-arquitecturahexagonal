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
public class CuentaResponse {

    private String id;
    private String clienteId;
    private String numeroCuenta;
    private BigDecimal saldo;
    private String estado;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;

}
