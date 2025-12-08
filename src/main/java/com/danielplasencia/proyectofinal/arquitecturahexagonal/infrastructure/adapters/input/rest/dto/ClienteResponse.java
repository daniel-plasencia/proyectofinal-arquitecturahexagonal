package com.danielplasencia.proyectofinal.arquitecturahexagonal.infrastructure.adapters.input.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteResponse {

    private String id;
    private String nombre;
    private String email;
    private String documento;
    private LocalDateTime fechaCreacion;

}
