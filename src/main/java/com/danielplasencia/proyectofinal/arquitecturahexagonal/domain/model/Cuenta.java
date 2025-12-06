package com.danielplasencia.proyectofinal.arquitecturahexagonal.domain.model;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.domain.excepciones.InvalidCuentaDataException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cuenta {



    private String cuenta_id;
    private String cliente_id;
    private String numero_cuenta;
    private BigDecimal saldo;
    private String estado;  // ACTIVO / CERRADO
    private LocalDateTime fecha_creacion;
    private LocalDateTime fecha_actualizacion;

    /**
     * Validaciones
     * saldo >= 0 <--- check
     * numero_cuenta unico
     * estado - valores permitidos: 'ACTIVO' o 'CERRADO' <-- check
     */

    public void validarCuentaInput(){
        if (!this.hasSaldoMoreThanZero()) {
            throw new InvalidCuentaDataException("El saldo es menor que 0");
        }

        if (!this.hasValidEstado()) {
            throw new InvalidCuentaDataException("El estado no es ACTIVO o CERRADO");
        }
    }

    public boolean hasSaldoMoreThanZero(){
        return ((saldo != null) && (saldo.compareTo(BigDecimal.ZERO) >= 0));
    }

    public boolean hasValidEstado(){
        return (Objects.equals(estado, "ACTIVO") || Objects.equals(estado, "CERRADO"));
    }

}
