package com.danielplasencia.proyectofinal.arquitecturahexagonal.application.ports.output;

import com.danielplasencia.proyectofinal.arquitecturahexagonal.domain.model.Cuenta;

import java.util.List;
import java.util.Optional;

public interface CuentaRepositoryPort {

    Cuenta save(Cuenta cuenta);
    Optional<Cuenta> findById(String accountId);
    List<Cuenta> findAll();
    Optional<Cuenta> findByNumeroCuenta(String numeroCuenta);

}
