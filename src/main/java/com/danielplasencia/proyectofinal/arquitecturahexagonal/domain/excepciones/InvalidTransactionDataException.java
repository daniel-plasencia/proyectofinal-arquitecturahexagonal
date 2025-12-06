package com.danielplasencia.proyectofinal.arquitecturahexagonal.domain.excepciones;

public class InvalidTransactionDataException extends RuntimeException {
    public InvalidTransactionDataException(String message) {
        super("Invalid Transaction data: "+message);
    }
}
