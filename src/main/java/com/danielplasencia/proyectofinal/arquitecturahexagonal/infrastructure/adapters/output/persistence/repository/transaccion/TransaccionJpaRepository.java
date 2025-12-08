package com.danielplasencia.proyectofinal.arquitecturahexagonal.infrastructure.adapters.output.persistence.repository.transaccion;

import com.danielplasencia.proyectofinal.arquitecturahexagonal.infrastructure.adapters.output.persistence.entity.TransaccionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransaccionJpaRepository extends JpaRepository<TransaccionEntity, String> {

    // Todas las transacciones hechas DESDE una cuenta
    List<TransaccionEntity> findByCuentaOrigenId(String cuentaOrigenId);

    // Todas las transacciones RECIBIDAS por una cuenta
    List<TransaccionEntity> findByCuentaDestinoId(String cuentaDestinoId);

}
