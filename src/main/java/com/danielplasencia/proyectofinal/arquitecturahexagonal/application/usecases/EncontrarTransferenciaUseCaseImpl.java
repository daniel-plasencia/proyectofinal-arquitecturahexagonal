package com.danielplasencia.proyectofinal.arquitecturahexagonal.application.usecases;


import com.danielplasencia.proyectofinal.arquitecturahexagonal.application.ports.input.ConsultarSaldoUseCase;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.application.ports.input.CrearCuentaUseCase;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.application.ports.input.EncontrarCuentaUseCase;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.application.ports.input.EncontrarTransferenciaUseCase;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.application.ports.output.ClienteRepositoryPort;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.application.ports.output.CuentaRepositoryPort;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.application.ports.output.TransaccionRepositoryPort;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.domain.excepciones.ClienteNotFoundException;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.domain.excepciones.CuentaNotFoundException;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.domain.excepciones.InvalidCuentaDataException;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.domain.excepciones.TransaccionNotFoundException;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.domain.model.Cuenta;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.domain.model.Transaccion;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@RequiredArgsConstructor
@Transactional
@Slf4j
public class EncontrarTransferenciaUseCaseImpl implements EncontrarTransferenciaUseCase {

    private final CuentaRepositoryPort cuentaRepositoryPort;
    private final ClienteRepositoryPort clienteRepositoryPort;
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

}
