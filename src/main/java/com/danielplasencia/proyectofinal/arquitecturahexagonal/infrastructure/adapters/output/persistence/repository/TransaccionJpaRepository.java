package com.danielplasencia.proyectofinal.arquitecturahexagonal.infrastructure.adapters.output.persistence.repository;

import com.danielplasencia.proyectofinal.arquitecturahexagonal.infrastructure.adapters.output.persistence.entity.ClienteEntity;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.infrastructure.adapters.output.persistence.entity.CuentaEntity;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.infrastructure.adapters.output.persistence.entity.TransaccionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TransaccionJpaRepository extends JpaRepository<TransaccionEntity, String> {

    // Todas las transacciones hechas DESDE una cuenta
    List<TransaccionEntity> findByCuentaOrigenId(String cuentaOrigenId);

    // Todas las transacciones RECIBIDAS por una cuenta
    List<TransaccionEntity> findByCuentaDestinoId(String cuentaDestinoId);

    // Opcional: historial entre dos cuentas
    List<TransaccionEntity> findByCuentaOrigenIdAndCuentaDestinoId(
            String cuentaOrigenId,
            String cuentaDestinoId
    );

}
