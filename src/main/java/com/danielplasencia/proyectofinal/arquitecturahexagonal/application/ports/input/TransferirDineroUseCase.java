package com.danielplasencia.proyectofinal.arquitecturahexagonal.application.ports.input;

import com.danielplasencia.proyectofinal.arquitecturahexagonal.domain.model.Transaccion;

public interface TransferirDineroUseCase {

    Transaccion transfer(Transaccion transaccionRequest);
}
