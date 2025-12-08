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
    private String cuentaId;

    @Column(name = "cliente_id", length = 50, nullable = false)
    private String clienteId;

    @Column(name = "numero_cuenta", length = 20, nullable = false, unique = true)
    private String numeroCuenta;

    @Column(name = "saldo", precision = 15, scale = 2, nullable = false)
    private BigDecimal saldo;

    @Column(name = "estado", length = 20, nullable = false)
    private String estado;  // ACTIVO / CERRADO

    @CreationTimestamp
    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    @UpdateTimestamp
    @Column(name = "fecha_actualizacion", nullable = false)
    private LocalDateTime fechaActualizacion;

}
