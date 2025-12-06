package com.danielplasencia.proyectofinal.arquitecturahexagonal.application.usecases;


import com.danielplasencia.proyectofinal.arquitecturahexagonal.application.ports.input.ConsultarSaldoUseCase;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.application.ports.input.CrearCuentaUseCase;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.application.ports.input.EncontrarCuentaUseCase;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.application.ports.output.ClienteRepositoryPort;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.application.ports.output.CuentaRepositoryPort;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.domain.excepciones.CuentaNotFoundException;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.domain.excepciones.InvalidCuentaDataException;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.domain.model.Cliente;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.domain.model.Cuenta;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
//import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@RequiredArgsConstructor
//@Transactional
@Slf4j
public class CuentaUseCasesImpl implements CrearCuentaUseCase, EncontrarCuentaUseCase, ConsultarSaldoUseCase {

    private final CuentaRepositoryPort cuentaRepositoryPort;
    private final ClienteRepositoryPort clienteRepositoryPort;

    @Override
    public BigDecimal consultarSaldo(String cuenta_id) {

        if (cuenta_id == null || cuenta_id.isBlank()) {
            throw new IllegalArgumentException("El ID de la cuenta no puede ser nulo ni vacío");
        }

        Cuenta cuenta = cuentaRepositoryPort.findById(cuenta_id)
                .orElseThrow(() -> new CuentaNotFoundException(cuenta_id));

        return cuenta.getSaldo();
    }

    @Override
    public Cuenta execute(Cuenta newCuenta) {

        if (newCuenta == null) {
            throw new InvalidCuentaDataException("La cuenta no puede ser nula");
        }

        if (newCuenta.getCliente_id() == null || newCuenta.getCliente_id().isBlank()) {
            throw new InvalidCuentaDataException("La cuenta debe tener un cliente asociado");
        }

        // Validamos que el cliente exista
        var clienteOpt = clienteRepositoryPort.findById(newCuenta.getCliente_id());
        if (clienteOpt.isEmpty()) {
            throw new InvalidCuentaDataException(
                    "No existe cliente con id: " + newCuenta.getCliente_id()
            );
        }

        return cuentaRepositoryPort.save(newCuenta);
    }

    @Override
    public Cuenta findCuentaById(String cuenta_id) {


        if (cuenta_id == null || cuenta_id.isBlank()) {
            throw new IllegalArgumentException("Invalid ID");
        }

        return cuentaRepositoryPort.findById(cuenta_id).orElseThrow(()->new CuentaNotFoundException(cuenta_id));
    }

    @Override
    public List<Cuenta> findAllCuenta() {
        log.info("Buscando todas las cuentas");
        List<Cuenta> cuentas = cuentaRepositoryPort.findAll();
        log.info("Se encontraron {} cuentas", cuentas.size());
        return cuentas;
    }
}
