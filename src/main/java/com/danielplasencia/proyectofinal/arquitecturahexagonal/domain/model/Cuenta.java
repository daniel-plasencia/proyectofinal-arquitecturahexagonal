package com.danielplasencia.proyectofinal.arquitecturahexagonal.domain.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.math.BigDecimal;

@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class Cuenta {



    private String cuenta_id;              // cuenta_id
    private String cliente_id;        // cliente_id (SOLO el id del cliente)
    private String numero_cuenta;    // numero_cuenta
    private BigDecimal saldo;       // saldo
    private String estado;          // ACTIVO / CERRADO
    private LocalDateTime fecha_creacion;
    private LocalDateTime fecha_actualizacion;

    public Cuenta(String cuenta_id, String cliente_id, String numero_cuenta, BigDecimal saldo, String estado, LocalDateTime fecha_creacion, LocalDateTime fecha_actualizacion) {
        this.cuenta_id = cuenta_id;
        this.cliente_id = cliente_id;
        this.numero_cuenta = numero_cuenta;
        this.saldo = saldo;
        this.estado = estado;
        this.fecha_creacion = fecha_creacion;
        this.fecha_actualizacion = fecha_actualizacion;
    }

    public Cuenta() {
    }

    public String getCuenta_id() {
        return cuenta_id;
    }

    public void setCuenta_id(String cuenta_id) {
        this.cuenta_id = cuenta_id;
    }

    public String getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(String cliente_id) {
        this.cliente_id = cliente_id;
    }

    public String getNumero_cuenta() {
        return numero_cuenta;
    }

    public void setNumero_cuenta(String numero_cuenta) {
        this.numero_cuenta = numero_cuenta;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDateTime getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(LocalDateTime fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public LocalDateTime getFecha_actualizacion() {
        return fecha_actualizacion;
    }

    public void setFecha_actualizacion(LocalDateTime fecha_actualizacion) {
        this.fecha_actualizacion = fecha_actualizacion;
    }
}
