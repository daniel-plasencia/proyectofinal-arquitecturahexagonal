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
public class CuentaRequest {

    private String clienteId;      // a qué cliente pertenece
    private String numeroCuenta;   // opcional, se puede generar en el usecase
    private BigDecimal saldo;      // opcional, el usecase puede poner 0 si viene null
    private String estado;         // opcional, default "ACTIVO"

}
