package com.danielplasencia.proyectofinal.arquitecturahexagonal.infrastructure.adapters.output.persistence.repository.transaccion;

import com.danielplasencia.proyectofinal.arquitecturahexagonal.application.ports.output.ClienteRepositoryPort;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.application.ports.output.TransaccionRepositoryPort;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.domain.model.Cliente;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.domain.model.Transaccion;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.infrastructure.adapters.output.persistence.entity.ClienteEntity;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.infrastructure.adapters.output.persistence.entity.TransaccionEntity;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.infrastructure.adapters.output.persistence.mapper.ClienteMapper;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.infrastructure.adapters.output.persistence.mapper.TransaccionMapper;
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
public class TransaccionRepositoryAdapter implements TransaccionRepositoryPort {

    private final TransaccionJpaRepository transaccionJpaRepository;
    private final TransaccionMapper transaccionMapper;


    @Override
    public Transaccion save(Transaccion transaccion) {
        TransaccionEntity entity = this.transaccionMapper.toEntity(transaccion);
        TransaccionEntity saved = this.transaccionJpaRepository.save(entity);
        return this.transaccionMapper.toDomain(saved);
    }

    @Override
    public Optional<Transaccion> findById(String transaccionId) {
        return this.transaccionJpaRepository.findById(transaccionId)
                .map(this.transaccionMapper::toDomain);
    }

    @Override
    public List<Transaccion> findAll() {
        List<TransaccionEntity> entities = this.transaccionJpaRepository.findAll();
        return this.transaccionMapper.toDomain(entities);
    }

    @Override
    public List<Transaccion> findByCuentaOrigenId(String cuentaOrigenId) {
        List<TransaccionEntity> entities =
                this.transaccionJpaRepository.findByCuentaOrigenId(cuentaOrigenId);
        return this.transaccionMapper.toDomain(entities);
    }

    @Override
    public List<Transaccion> findByCuentaDestinoId(String cuentaDestinoId) {
        List<TransaccionEntity> entities =
                this.transaccionJpaRepository.findByCuentaDestinoId(cuentaDestinoId);
        return this.transaccionMapper.toDomain(entities);
    }
}
