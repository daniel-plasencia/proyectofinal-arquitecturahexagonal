package com.danielplasencia.proyectofinal.arquitecturahexagonal.domain.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

    private int id;
    private String nombre;
    private String email;
    private String documento;
    private LocalDateTime fecha_creacion;



    /*

    ├─────────────────────────────────┤
│ PK  id: VARCHAR(50)             │
│     nombre: VARCHAR(100)        │
│     email: VARCHAR(100)         │
│     documento: VARCHAR(20)      │
│     fecha_creacion: TIMESTAMP

    */

}
