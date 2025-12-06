package com.danielplasencia.proyectofinal.arquitecturahexagonal.application.ports.input;

import com.danielplasencia.proyectofinal.arquitecturahexagonal.domain.model.Cuenta;

public interface CrearCuentaUseCase {

    Cuenta execute(Cuenta newCuenta);

}
