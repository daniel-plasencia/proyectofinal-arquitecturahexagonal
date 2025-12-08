package com.danielplasencia.proyectofinal.arquitecturahexagonal.application.ports.input;

import com.danielplasencia.proyectofinal.arquitecturahexagonal.domain.model.Cliente;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.domain.model.Cuenta;

import java.util.List;

public interface EncontrarClienteUseCase {

    Cliente findClienteById(String cliente_id);
    List<Cliente> findAllCliente();
}
