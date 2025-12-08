package com.danielplasencia.proyectofinal.arquitecturahexagonal.infrastructure.adapters.output.persistence.repository.cuenta;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.infrastructure.adapters.output.persistence.entity.CuentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CuentaJpaRepository extends JpaRepository<CuentaEntity, String> {
    Optional<CuentaEntity> findByNumeroCuenta(String numeroCuenta);
    List<CuentaEntity> findByClienteId(String clienteId);
}
