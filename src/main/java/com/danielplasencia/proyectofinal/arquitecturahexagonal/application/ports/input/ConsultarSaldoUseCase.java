package com.danielplasencia.proyectofinal.arquitecturahexagonal.application.ports.input;

import java.math.BigDecimal;

public interface ConsultarSaldoUseCase {

    BigDecimal consultarSaldo(String cuenta_id);

}
