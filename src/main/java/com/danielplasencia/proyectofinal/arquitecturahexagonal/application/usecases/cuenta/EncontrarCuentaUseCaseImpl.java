package com.danielplasencia.proyectofinal.arquitecturahexagonal.application.usecases.cuenta;


import com.danielplasencia.proyectofinal.arquitecturahexagonal.application.ports.input.EncontrarCuentaUseCase;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.application.ports.output.ClienteRepositoryPort;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.application.ports.output.CuentaRepositoryPort;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.domain.excepciones.CuentaNotFoundException;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.domain.model.Cuenta;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@RequiredArgsConstructor
@Transactional
@Slf4j
public class EncontrarCuentaUseCaseImpl implements EncontrarCuentaUseCase {

    private final CuentaRepositoryPort cuentaRepositoryPort;

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

    @Override
    public List<Cuenta> findByClienteId(String clienteId) {
        if (clienteId == null || clienteId.isBlank()) {
            throw new IllegalArgumentException("clienteId no puede ser vacío");
        }
        return cuentaRepositoryPort.findByClienteId(clienteId);
    }

}
