package com.danielplasencia.proyectofinal.arquitecturahexagonal.application.usecases.transaccion;


import com.danielplasencia.proyectofinal.arquitecturahexagonal.application.ports.input.EncontrarTransaccionUseCase;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.application.ports.output.TransaccionRepositoryPort;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.domain.excepciones.TransaccionNotFoundException;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.domain.model.Transaccion;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@RequiredArgsConstructor
@Transactional
@Slf4j
public class EncontrarTransaccionUseCaseImpl implements EncontrarTransaccionUseCase {

    private final TransaccionRepositoryPort transaccionRepositoryPort;

    @Override
    @Transactional(readOnly = true)
    public Transaccion findTransaccionById(String transaccion_id) {


        if (transaccion_id == null || transaccion_id.isBlank()) {
            throw new IllegalArgumentException("Invalid ID");
        }

        return transaccionRepositoryPort.findById(transaccion_id).orElseThrow(()->new TransaccionNotFoundException(transaccion_id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Transaccion> findAllTransaccion() {
        log.info("Buscando todas las transacciones");
        List<Transaccion> transacciones = transaccionRepositoryPort.findAll();
        log.info("Se encontraron {} transaccciones", transacciones.size());
        return transacciones;
    }

    @Override
    public List<Transaccion> findByCuentaOrigen(String cuentaOrigenId) {
        if (cuentaOrigenId == null || cuentaOrigenId.isBlank()) {
            throw new IllegalArgumentException("cuentaOrigenId no puede ser vacío");
        }
        log.info("Buscando transacciones con cuenta_origen_id={}", cuentaOrigenId);
        return transaccionRepositoryPort.findByCuentaOrigenId(cuentaOrigenId);
    }

    @Override
    public List<Transaccion> findByCuentaDestino(String cuentaDestinoId) {
        if (cuentaDestinoId == null || cuentaDestinoId.isBlank()) {
            throw new IllegalArgumentException("cuentaDestinoId no puede ser vacío");
        }
        log.info("Buscando transacciones con cuenta_destino_id={}", cuentaDestinoId);
        return transaccionRepositoryPort.findByCuentaDestinoId(cuentaDestinoId);
    }

}
