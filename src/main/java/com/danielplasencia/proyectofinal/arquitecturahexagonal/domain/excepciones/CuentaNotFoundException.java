package com.danielplasencia.proyectofinal.arquitecturahexagonal.domain.excepciones;

public class CuentaNotFoundException extends RuntimeException {
    public CuentaNotFoundException(String cuentaId) {
        super("Cuenta no encontrada, con ID: "+cuentaId);
    }
}
