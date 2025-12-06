package com.danielplasencia.proyectofinal.arquitecturahexagonal.domain.excepciones;

public class InvalidCuentaDataException extends RuntimeException {
    public InvalidCuentaDataException(String message) {
        super("Invalid Account data: "+message);
    }
}
