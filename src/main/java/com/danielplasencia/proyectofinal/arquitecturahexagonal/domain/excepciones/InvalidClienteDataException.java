package com.danielplasencia.proyectofinal.arquitecturahexagonal.domain.excepciones;

public class InvalidClienteDataException extends RuntimeException {
  public InvalidClienteDataException(String message) {
    super("Invalid Client data: " + message);
  }
}
