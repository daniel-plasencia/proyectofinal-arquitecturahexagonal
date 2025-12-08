package com.danielplasencia.proyectofinal.arquitecturahexagonal.infrastructure.adapters.output.persistence.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "CUENTA")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CuentaEntity {

    @Id
    @Column(name = "cuenta_id", length = 50, nullable = false)
    private String cuenta_id;

    @Column(name = "cliente_id", length = 50, nullable = false)
    private String cliente_id;

    @Column(name = "numero_cuenta", length = 20, nullable = false, unique = true)
    private String numero_cuenta;

    @Column(name = "saldo", precision = 15, scale = 2, nullable = false)
    private BigDecimal saldo;

    @Column(name = "estado", length = 20, nullable = false)
    private String estado;  // ACTIVO / CERRADO

    @CreationTimestamp
    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fecha_creacion;

    @UpdateTimestamp
    @Column(name = "fecha_actualizacion", nullable = false)
    private LocalDateTime fecha_actualizacion;

}
