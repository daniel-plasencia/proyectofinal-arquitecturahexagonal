package com.danielplasencia.proyectofinal.arquitecturahexagonal.application.ports.output;

import com.danielplasencia.proyectofinal.arquitecturahexagonal.domain.model.Cliente;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;

public interface ClienteRepositoryPort {

    Cliente save(Cliente cliente);
    Optional<Cliente> findById(String clientId);
    Optional<Cliente> findByDocumento(String documento);

}
