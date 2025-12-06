package com.danielplasencia.proyectofinal.arquitecturahexagonal.domain.excepciones;

public class TransaccionNotFoundException extends RuntimeException {
    public TransaccionNotFoundException(String transaccionId) {
        super("Transaccion no encontrada, con ID: "+transaccionId);
    }
}
