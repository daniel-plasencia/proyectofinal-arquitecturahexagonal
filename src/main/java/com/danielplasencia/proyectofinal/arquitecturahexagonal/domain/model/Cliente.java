package com.danielplasencia.proyectofinal.arquitecturahexagonal.domain.model;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.domain.excepciones.InvalidCuentaDataException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cliente {

    private int id;
    private String nombre;
    private String email;
    private String documento;
    private LocalDateTime fecha_creacion;

    /**
     * Validaciones
     * Email formato válido <--- check
     * Documento único en el sistema
     */

    public void validarClienteInput(){

        if (!this.hasValidEmail()) {
            throw new InvalidCuentaDataException("Formato de email inválido");
        }
    }

    public boolean hasValidEmail() {
        return email != null &&
                email.contains("@") &&
                email.contains(".") &&
                email.length() > 5;
    }


}
