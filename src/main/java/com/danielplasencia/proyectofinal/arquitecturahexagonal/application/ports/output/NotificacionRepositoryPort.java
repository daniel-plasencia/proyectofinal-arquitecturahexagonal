package com.danielplasencia.proyectofinal.arquitecturahexagonal.application.ports.output;

public interface NotificacionRepositoryPort {

    void notificar(String clienteId, String mensaje);

}
