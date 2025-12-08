package com.danielplasencia.proyectofinal.arquitecturahexagonal.application.usecases.cuenta;


import com.danielplasencia.proyectofinal.arquitecturahexagonal.application.ports.input.ConsultarSaldoUseCase;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.application.ports.output.CuentaRepositoryPort;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.domain.excepciones.CuentaNotFoundException;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.domain.model.Cuenta;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;


@RequiredArgsConstructor
@Transactional
@Slf4j
public class ConsultarSaldoUseCaseImpl implements ConsultarSaldoUseCase {

    private final CuentaRepositoryPort cuentaRepositoryPort;

    @Override
    public BigDecimal consultarSaldo(String cuenta_id) {

        if (cuenta_id == null || cuenta_id.isBlank()) {
            throw new IllegalArgumentException("El ID de la cuenta no puede ser nulo ni vacío");
        }

        Cuenta cuenta = cuentaRepositoryPort.findById(cuenta_id)
                .orElseThrow(() -> new CuentaNotFoundException(cuenta_id));

        return cuenta.getSaldo();
    }

}
