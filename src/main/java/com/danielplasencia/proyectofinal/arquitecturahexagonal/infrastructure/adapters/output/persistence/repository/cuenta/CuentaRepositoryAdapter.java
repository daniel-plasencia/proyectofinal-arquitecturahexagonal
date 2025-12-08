package com.danielplasencia.proyectofinal.arquitecturahexagonal.infrastructure.adapters.output.persistence.repository.cuenta;

import com.danielplasencia.proyectofinal.arquitecturahexagonal.application.ports.output.ClienteRepositoryPort;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.application.ports.output.CuentaRepositoryPort;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.domain.model.Cliente;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.domain.model.Cuenta;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.infrastructure.adapters.output.persistence.entity.ClienteEntity;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.infrastructure.adapters.output.persistence.entity.CuentaEntity;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.infrastructure.adapters.output.persistence.mapper.ClienteMapper;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.infrastructure.adapters.output.persistence.mapper.CuentaMapper;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.infrastructure.adapters.output.persistence.repository.cliente.ClienteJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// Adapter implementation
@Repository
@RequiredArgsConstructor
@Slf4j
public class CuentaRepositoryAdapter implements CuentaRepositoryPort {

    private final CuentaJpaRepository cuentaJpaRepository;
    private final CuentaMapper cuentaMapper;

    @Override
    public Cuenta save(Cuenta cuenta) {
        log.info("Guardando cuenta: {}",cuenta);
        CuentaEntity entity = this.cuentaMapper.toEntity(cuenta);
        CuentaEntity saved = this.cuentaJpaRepository.save(entity);
        log.info("Cuenta guardada con Spring Data: {}", saved);
        return this.cuentaMapper.toDomain(saved);
    }

    @Override
    public Optional<Cuenta> findById(String cuentaId) {
        log.info("Buscando cuenta por ID: {}", cuentaId);
        return this.cuentaJpaRepository.findById(cuentaId)
                .map(this.cuentaMapper::toDomain);
    }

    @Override
    public List<Cuenta> findAll() {
        log.info("Buscando todas las cuentas");
        List<CuentaEntity> entities = this.cuentaJpaRepository.findAll();
        return this.cuentaMapper.toDomain(entities);
    }

    @Override
    public Optional<Cuenta> findByNumeroCuenta(String numeroCuenta) {
        return this.cuentaJpaRepository.findByNumeroCuenta(numeroCuenta)
                .map(this.cuentaMapper::toDomain);

    }

    @Override
    public List<Cuenta> findByClienteId(String clienteId) {
        List<CuentaEntity> entities = this.cuentaJpaRepository.findByClienteId(clienteId);
        return this.cuentaMapper.toDomain(entities);
    }
}
