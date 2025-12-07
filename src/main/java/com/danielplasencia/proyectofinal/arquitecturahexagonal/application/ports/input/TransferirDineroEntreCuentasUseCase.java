package com.danielplasencia.proyectofinal.arquitecturahexagonal.application.ports.input;

import com.danielplasencia.proyectofinal.arquitecturahexagonal.domain.model.Transaccion;

import java.math.BigDecimal;

public interface TransferirDineroEntreCuentasUseCase {

    Transaccion transfer(Transaccion transaccionRequest);
}
