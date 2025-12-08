package com.danielplasencia.proyectofinal.arquitecturahexagonal.application.ports.output;

import com.danielplasencia.proyectofinal.arquitecturahexagonal.domain.model.Cliente;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.domain.model.Transaccion;

import java.util.List;
import java.util.Optional;

public interface TransaccionRepositoryPort {

    Transaccion save(Transaccion transaccion);
    Optional<Transaccion> findById(String transaccionId);
    List<Transaccion> findAll();
    List<Transaccion> findByCuentaOrigenId(String cuentaOrigenId);
    List<Transaccion> findByCuentaDestinoId(String cuentaDestinoId);
}
