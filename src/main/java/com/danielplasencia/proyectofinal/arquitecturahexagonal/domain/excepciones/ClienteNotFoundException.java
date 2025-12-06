package com.danielplasencia.proyectofinal.arquitecturahexagonal.domain.excepciones;

public class ClienteNotFoundException extends RuntimeException {
    public ClienteNotFoundException(String clientId) {
        super("Cliente no encontrado con ID: " + clientId);
    }
}
