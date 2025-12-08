package com.danielplasencia.proyectofinal.arquitecturahexagonal.infrastructure.adapters.output.persistence.repository;

import com.danielplasencia.proyectofinal.arquitecturahexagonal.application.ports.output.ClienteRepositoryPort;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.domain.model.Cliente;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

// Adapter implementation
@Repository
@RequiredArgsConstructor
@Slf4j
public class ClienteRepositoryAdapter implements ClienteRepositoryPort {

    private final UserJpaRepository jpaRepository;
    private final UserMapper mapper;

    @Override
    public Cliente save(Cliente cliente) {
        return null;
    }

    @Override
    public Optional<Cliente> findById(String clientId) {
        return Optional.empty();
    }

    @Override
    public Optional<Cliente> findByDocumento(String documento) {
        return Optional.empty();
    }

    @Override
    public List<Cliente> findAll() {
        return List.of();
    }
}
