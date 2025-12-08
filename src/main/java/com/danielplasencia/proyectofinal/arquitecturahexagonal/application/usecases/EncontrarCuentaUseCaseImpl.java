package com.danielplasencia.proyectofinal.arquitecturahexagonal.application.usecases;


import com.danielplasencia.proyectofinal.arquitecturahexagonal.application.ports.input.ConsultarSaldoUseCase;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.application.ports.input.CrearCuentaUseCase;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.application.ports.input.EncontrarCuentaUseCase;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.application.ports.output.ClienteRepositoryPort;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.application.ports.output.CuentaRepositoryPort;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.domain.excepciones.ClienteNotFoundException;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.domain.excepciones.CuentaNotFoundException;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.domain.excepciones.InvalidCuentaDataException;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.domain.model.Cuenta;
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
public class EncontrarCuentaUseCaseImpl implements EncontrarCuentaUseCase {

    private final CuentaRepositoryPort cuentaRepositoryPort;
    private final ClienteRepositoryPort clienteRepositoryPort;

    @Override
    @Transactional(readOnly = true)
    public Cuenta findCuentaById(String cuenta_id) {


        if (cuenta_id == null || cuenta_id.isBlank()) {
            throw new IllegalArgumentException("Invalid ID");
        }

        return cuentaRepositoryPort.findById(cuenta_id).orElseThrow(()->new CuentaNotFoundException(cuenta_id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cuenta> findAllCuenta() {
        log.info("Buscando todas las cuentas");
        List<Cuenta> cuentas = cuentaRepositoryPort.findAll();
        log.info("Se encontraron {} cuentas", cuentas.size());
        return cuentas;
    }

}
