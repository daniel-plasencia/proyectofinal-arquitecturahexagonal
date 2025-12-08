package com.danielplasencia.proyectofinal.arquitecturahexagonal.application.ports.input;

import com.danielplasencia.proyectofinal.arquitecturahexagonal.domain.model.Cuenta;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.domain.model.Transaccion;

import java.util.List;

public interface EncontrarTransferenciaUseCase {

    Transaccion findTransaccionById(String transaccion_id);
    List<Transaccion> findAllTransaccion();
}
