package com.danielplasencia.proyectofinal.arquitecturahexagonal.infrastructure.adapters.output.persistence.repository.cliente;

import com.danielplasencia.proyectofinal.arquitecturahexagonal.application.ports.output.ClienteRepositoryPort;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.domain.model.Cliente;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.infrastructure.adapters.output.persistence.entity.ClienteEntity;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.infrastructure.adapters.output.persistence.mapper.ClienteMapper;
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

    private final ClienteJpaRepository clienteJpaRepository;
    private final ClienteMapper clienteMapper;


    @Override
    public Cliente save(Cliente cliente) {
        // Domain -> Entity
        ClienteEntity entity = this.clienteMapper.toEntity(cliente);
        // JPA save
        ClienteEntity saved = this.clienteJpaRepository.save(entity);
        // Entity -> Domain
        return this.clienteMapper.toDomain(saved);
    }

    @Override
    public Optional<Cliente> findById(String clientId) {
        return clienteJpaRepository.findById(clientId)
                .map(clienteMapper::toDomain);
    }

    @Override
    public Optional<Cliente> findByDocumento(String documento) {
        return this.clienteJpaRepository.findByDocumento(documento)
                .map(this.clienteMapper::toDomain);
    }

    @Override
    public Optional<Cliente> findByEmail(String email) {
        return this.clienteJpaRepository.findByEmail(email)
                .map(this.clienteMapper::toDomain);
    }

    @Override
    public List<Cliente> findAll() {
        List<ClienteEntity> entities = this.clienteJpaRepository.findAll();
        return this.clienteMapper.toDomain(entities);
    }

    @Override
    public boolean existsByEmail(String email) {
        return this.clienteJpaRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByDocumento(String documento) {
        return this.clienteJpaRepository.existsByDocumento(documento);
    }
}
