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
@Table(name = "TRANSACCION")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransaccionEntity {

    @Id
    @Column(name = "transaccion_id", length = 50, nullable = false)
    private String transaccion_id;

    @Column(name = "cuenta_origen_id", length = 50, nullable = false)
    private String cuenta_origen_id;

    @Column(name = "cuenta_destino_id", length = 50, nullable = false)
    private String cuenta_destino_id;

    @Column(name = "monto", precision = 15, scale = 2, nullable = false)
    private BigDecimal monto;

    @Column(name = "comision", precision = 15, scale = 2, nullable = false)
    private BigDecimal comision;

    @Column(name = "tipo", length = 20, nullable = false)
    private String tipo;  // TRANSFERENCIA / DEPOSITO / RETIRO

    @Column(name = "estado", length = 20, nullable = false)
    private String estado;  // PENDIENTE / COMPLETADA / FALLIDA / CANCELADA

    @Column(name = "descripcion", length = 255)
    private String descripcion;

    @CreationTimestamp
    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fecha_creacion;

}
