package com.danielplasencia.proyectofinal.arquitecturahexagonal.infrastructure.adapters.output.persistence.repository.cliente;

import com.danielplasencia.proyectofinal.arquitecturahexagonal.infrastructure.adapters.output.persistence.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteJpaRepository extends JpaRepository<ClienteEntity, String> {

    Optional<ClienteEntity> findByEmail(String email);
    Optional<ClienteEntity> findByDocumento(String documento);
    boolean existsByEmail(String email);
    boolean existsByDocumento(String documento);

}
