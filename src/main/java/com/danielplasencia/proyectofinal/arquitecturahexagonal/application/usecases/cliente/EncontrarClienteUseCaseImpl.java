package com.danielplasencia.proyectofinal.arquitecturahexagonal.application.usecases.cliente;


import com.danielplasencia.proyectofinal.arquitecturahexagonal.application.ports.input.EncontrarClienteUseCase;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.application.ports.output.ClienteRepositoryPort;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.application.ports.output.CuentaRepositoryPort;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.domain.excepciones.ClienteNotFoundException;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.domain.model.Cliente;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@RequiredArgsConstructor
@Transactional
@Slf4j
public class EncontrarClienteUseCaseImpl implements EncontrarClienteUseCase {

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
