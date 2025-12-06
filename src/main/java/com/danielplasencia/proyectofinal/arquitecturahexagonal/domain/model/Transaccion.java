package com.danielplasencia.proyectofinal.arquitecturahexagonal.domain.model;

import com.danielplasencia.proyectofinal.arquitecturahexagonal.domain.excepciones.InvalidTransactionDataException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
//@Setter
public class Transaccion {

    private String transaccion_id;                 // transaccion_id
    private String cuenta_origen_id;     // cuenta_origen_id
    private String cuenta_destino_id;    // cuenta_destino_id
    private BigDecimal monto;          // monto
    private BigDecimal comision;       // comision
    private String tipo;               // TRANSFERENCIA / DEPOSITO / RETIRO
    private String estado;             // PENDIENTE / COMPLETADA / ...
    private String descripcion;
    private LocalDateTime fecha_creacion;

    /**
     * Validaciones
     * monto > 0 <--- check
     * cuenta_origen_id ≠ cuenta_destino_id <----- check
     * tipo valores: 'TRANSFERENCIA', 'DEPOSITO', 'RETIRO' <---- check
     * estado valores: 'PENDIENTE', 'COMPLETADA', 'FALLIDA', 'CANCELADA' <---- check
     */

    public void validarTransaccionInput(){
        if (!this.hasMontoMoreThanZero()) {
            throw new InvalidTransactionDataException("El monto es menor que 0");
        }

        if (!this.hasValidTipoValores()) {
            throw new InvalidTransactionDataException("El tipo no es TRANSFERENCIA, DEPOSITO o RETIRO");
        }

        if (!this.hasValidEstadoValores()) {
            throw new InvalidTransactionDataException("El estado no es PENDIENTE, COMPLETADA, FALLIDA, CANCELADA");
        }

        if (!this.hasCuentaOrigenDiferenteCuentaDestino()) {
            throw new InvalidTransactionDataException("Las cuentas origen y destino no son diferentes");
        }
    }

    public boolean hasMontoMoreThanZero(){
        return ((monto != null) && (monto.compareTo(BigDecimal.ZERO) > 0));
    }

    public boolean hasCuentaOrigenDiferenteCuentaDestino(){
        return (!Objects.equals(cuenta_origen_id, cuenta_destino_id));
    }

    public boolean hasValidTipoValores(){
        return (Objects.equals(estado, "TRANSFERENCIA") || Objects.equals(estado, "DEPOSITO") || Objects.equals(estado, "RETIRO"));
    }

    public boolean hasValidEstadoValores(){
        return (Objects.equals(estado, "PENDIENTE") || Objects.equals(estado, "COMPLETADA") || Objects.equals(estado, "FALLIDA") || Objects.equals(estado, "CANCELADA"));
    }
}
