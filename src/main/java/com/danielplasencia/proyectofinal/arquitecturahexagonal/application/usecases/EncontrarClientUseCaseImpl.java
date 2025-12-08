package com.danielplasencia.proyectofinal.arquitecturahexagonal.application.usecases;


import com.danielplasencia.proyectofinal.arquitecturahexagonal.application.ports.input.ConsultarSaldoUseCase;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.application.ports.input.CrearCuentaUseCase;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.application.ports.input.EncontrarClienteUseCase;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.application.ports.input.EncontrarCuentaUseCase;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.application.ports.output.ClienteRepositoryPort;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.application.ports.output.CuentaRepositoryPort;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.domain.excepciones.ClienteNotFoundException;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.domain.excepciones.CuentaNotFoundException;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.domain.excepciones.InvalidCuentaDataException;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.domain.model.Cliente;
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
public class EncontrarClientUseCaseImpl implements EncontrarClienteUseCase {

    private final CuentaRepositoryPort cuentaRepositoryPort;
    private final ClienteRepositoryPort clienteRepositoryPort;

    @Override
    @Transactional(readOnly = true)
    public Cliente findClienteById(String cliente_id) {


        if (cliente_id == null || cliente_id.isBlank()) {
            throw new IllegalArgumentException("Invalid ID");
        }

        return clienteRepositoryPort.findById(cliente_id).orElseThrow(()->new ClienteNotFoundException(cliente_id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> findAllCliente() {
        log.info("Buscando todas los clientes");
        List<Cliente> clientes = clienteRepositoryPort.findAll();
        log.info("Se encontraron {} clientes", clientes.size());
        return clientes;
    }

}
