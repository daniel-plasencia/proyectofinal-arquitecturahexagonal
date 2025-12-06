package com.danielplasencia.proyectofinal.arquitecturahexagonal.application.ports.input;

import com.danielplasencia.proyectofinal.arquitecturahexagonal.domain.model.Cuenta;
import java.util.List;

public interface EncontrarCuentaUseCase {

    Cuenta findCuentaById(String cuenta_id);
    List<Cuenta> findAllCuenta();
}
