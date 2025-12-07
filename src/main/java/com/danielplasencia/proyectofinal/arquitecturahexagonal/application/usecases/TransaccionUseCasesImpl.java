package com.danielplasencia.proyectofinal.arquitecturahexagonal.application.usecases;

import com.danielplasencia.proyectofinal.arquitecturahexagonal.application.ports.input.TransferirDineroEntreCuentasUseCase;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.application.ports.output.CuentaRepositoryPort;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.application.ports.output.NotificacionPort;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.application.ports.output.TransaccionRepositoryPort;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.domain.excepciones.InvalidTransactionDataException;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.domain.model.Transaccion;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@RequiredArgsConstructor
@Transactional
@Slf4j
public class TransaccionUseCasesImpl implements TransferirDineroEntreCuentasUseCase {

    private final CuentaRepositoryPort cuentaRepositoryPort;
    private final TransaccionRepositoryPort transaccionRepositoryPort;
    private final NotificacionPort notificacionPort;

    @Override
    public Transaccion transfer(String cuentaOrigenId,
                                String cuentaDestinoId,
                                BigDecimal monto,
                                String descripcion) {
        // ===== 1. Validaciones iniciales =====
        if (cuentaOrigenId == null || cuentaOrigenId.isBlank()
                || cuentaDestinoId == null || cuentaDestinoId.isBlank()) {
            throw new InvalidTransactionDataException("Las cuentas origen y destino no pueden ser nulas ni vacías");
        }

        return null;
    }
}
