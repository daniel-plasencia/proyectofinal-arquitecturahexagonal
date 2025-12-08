package com.danielplasencia.proyectofinal.arquitecturahexagonal.application.ports.output;

import com.danielplasencia.proyectofinal.arquitecturahexagonal.domain.model.Cliente;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.domain.model.Cuenta;

import java.util.List;
import java.util.Optional;

public interface ClienteRepositoryPort {

    Cliente save(Cliente cliente);
    Optional<Cliente> findById(String clientId);
    Optional<Cliente> findByDocumento(String documento);
    Optional<Cliente> findByEmail(String email);
    List<Cliente> findAll();
    boolean existsByEmail(String email);
    boolean existsByDocumento(String documento);
}
