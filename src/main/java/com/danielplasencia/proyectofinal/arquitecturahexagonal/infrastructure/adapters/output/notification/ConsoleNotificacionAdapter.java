package com.danielplasencia.proyectofinal.arquitecturahexagonal.infrastructure.adapters.output.notification;

import com.danielplasencia.proyectofinal.arquitecturahexagonal.application.ports.output.NotificacionRepositoryPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ConsoleNotificacionAdapter implements NotificacionRepositoryPort {


    @Override
    public void notificar(String clienteId, String mensaje) {
        log.info("NOTIFICACIÓN → Cliente {} : {}", clienteId, mensaje);
        System.out.println("NOTIFICACIÓN → Cliente " + clienteId + " : " + mensaje);
    }
}
