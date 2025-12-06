package com.danielplasencia.proyectofinal.arquitecturahexagonal.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class Transaccion {

    /*

    │ PK  transaccion_id: VARCHAR(50) │
│ FK  cuenta_origen_id: VARCHAR   │
│ FK  cuenta_destino_id: VARCHAR  │
│     monto: DECIMAL(15,2)        │
│     comision: DECIMAL(15,2)     │
│     tipo: VARCHAR(20)           │
│     estado: VARCHAR(20)         │
│     descripcion: VARCHAR(255)   │
│     fecha_creacion: TIMESTAMP   │
└─────────────────────────────────

     */

    private String transaccion_id;                 // transaccion_id
    private String cuenta_origen_id;     // cuenta_origen_id
    private String cuenta_destino_id;    // cuenta_destino_id
    private BigDecimal monto;          // monto
    private BigDecimal comision;       // comision
    private String tipo;               // TRANSFERENCIA / DEPOSITO / RETIRO
    private String estado;             // PENDIENTE / COMPLETADA / ...
    private String descripcion;
    private LocalDateTime fecha_creacion;

    public Transaccion() {
    }

    public Transaccion(String transaccion_id, String cuenta_origen_id, String cuenta_destino_id, BigDecimal monto, BigDecimal comision, String tipo, String estado, String descripcion, LocalDateTime fecha_creacion) {
        this.transaccion_id = transaccion_id;
        this.cuenta_origen_id = cuenta_origen_id;
        this.cuenta_destino_id = cuenta_destino_id;
        this.monto = monto;
        this.comision = comision;
        this.tipo = tipo;
        this.estado = estado;
        this.descripcion = descripcion;
        this.fecha_creacion = fecha_creacion;
    }

    public String getTransaccion_id() {
        return transaccion_id;
    }

    public void setTransaccion_id(String transaccion_id) {
        this.transaccion_id = transaccion_id;
    }

    public String getCuenta_origen_id() {
        return cuenta_origen_id;
    }

    public void setCuenta_origen_id(String cuenta_origen_id) {
        this.cuenta_origen_id = cuenta_origen_id;
    }

    public String getCuenta_destino_id() {
        return cuenta_destino_id;
    }

    public void setCuenta_destino_id(String cuenta_destino_id) {
        this.cuenta_destino_id = cuenta_destino_id;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public BigDecimal getComision() {
        return comision;
    }

    public void setComision(BigDecimal comision) {
        this.comision = comision;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDateTime getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(LocalDateTime fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }
}
