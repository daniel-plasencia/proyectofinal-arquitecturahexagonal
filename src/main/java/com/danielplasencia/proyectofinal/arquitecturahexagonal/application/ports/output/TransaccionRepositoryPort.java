package com.danielplasencia.proyectofinal.arquitecturahexagonal.application.ports.output;

import com.danielplasencia.proyectofinal.arquitecturahexagonal.domain.model.Transaccion;

public interface TransaccionRepositoryPort {

    Transaccion save(Transaccion transaccion);

}
