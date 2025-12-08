package com.danielplasencia.proyectofinal.arquitecturahexagonal.application.deprecated;


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

        // Valida que la cuenta no sea null
        if (newCuenta == null) {
            throw new InvalidCuentaDataException("La cuenta no puede ser nula");
        }

        // Domain validation
        //newCuenta.validarCuentaInput();

        // Valida que tenga un cliente_id
        if (newCuenta.getCliente_id() == null || newCuenta.getCliente_id().isBlank()) {
            throw new InvalidCuentaDataException("La cuenta debe tener un cliente asociado");
        }

        // Validar si cliente existe
        clienteRepositoryPort.findById(newCuenta.getCliente_id())
                .orElseThrow(() -> new ClienteNotFoundException(
                        "No existe cliente con id: " + newCuenta.getCliente_id()));


        /**
         * SETEA ID y NUMERO DE CUENTA
          */

        // Generar ID si no viene
        if (newCuenta.getCuenta_id() == null || newCuenta.getCuenta_id().isBlank()) {
            newCuenta.setCuenta_id(UUID.randomUUID().toString());
        }

        // Generar número de cuenta si no viene
        if (newCuenta.getNumero_cuenta() == null || newCuenta.getNumero_cuenta().isBlank()) {
            newCuenta.setNumero_cuenta(generarNumeroCuenta());
        }

        /**
         * VALIDACIONES QUE PIDE EL EJERCICIO ADICIONALES A LAS QUE SE HACEN EN EL MISMO MODEL
         */

        // Validar número de cuenta único
        cuentaRepositoryPort.findByNumeroCuenta(newCuenta.getNumero_cuenta())
                .ifPresent(existing -> {
                    throw new InvalidCuentaDataException(
                            "El número de cuenta ya existe: " + newCuenta.getNumero_cuenta());
                });


        if (newCuenta.getSaldo() == null) {
            newCuenta.setSaldo(BigDecimal.ZERO);
        }

        if (newCuenta.getEstado() == null || newCuenta.getEstado().isBlank()) {
            newCuenta.setEstado("ACTIVO");
        }

        // Setear fecha de creacion si no viene y actualizar fecha de actualización
        if (newCuenta.getFecha_creacion() == null) {
            newCuenta.setFecha_creacion(LocalDateTime.now());
        }
        newCuenta.setFecha_actualizacion(LocalDateTime.now());

        // Domain validation
        newCuenta.validarCuentaInput();

        return cuentaRepositoryPort.save(newCuenta);
    }

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

    // ===================== Helpers privados =====================
    private String generarNumeroCuenta() {
        // Para generar numeros de cuenta
        return "ACC-" + System.currentTimeMillis();
    }
}
