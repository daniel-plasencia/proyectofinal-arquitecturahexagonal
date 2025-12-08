package com.danielplasencia.proyectofinal.arquitecturahexagonal.application.usecases.transaccion;

import com.danielplasencia.proyectofinal.arquitecturahexagonal.application.ports.input.TransferirDineroUseCase;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.application.ports.output.CuentaRepositoryPort;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.application.ports.output.NotificacionRepositoryPort;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.application.ports.output.TransaccionRepositoryPort;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.domain.excepciones.CuentaNotFoundException;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.domain.excepciones.InvalidTransactionDataException;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.domain.model.Cuenta;
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
public class TransferirDineroUseCaseImpl implements TransferirDineroUseCase {

    private final CuentaRepositoryPort cuentaRepositoryPort;
    private final TransaccionRepositoryPort transaccionRepositoryPort;
    private final NotificacionRepositoryPort notificacionRepositoryPort;

    private static final BigDecimal COMISION_FIJA = new BigDecimal("5.00");


    @Override
    public Transaccion transfer(Transaccion transaccionRequest) {

        if (transaccionRequest == null) {
            throw new InvalidTransactionDataException("La transacción no puede ser nula");
        }

        String cuentaOrigenId = transaccionRequest.getCuenta_origen_id();
        String cuentaDestinoId = transaccionRequest.getCuenta_destino_id();
        BigDecimal monto = transaccionRequest.getMonto();
        String tipo = transaccionRequest.getTipo();
        String descripcion = transaccionRequest.getDescripcion();

        // 1) Validaciones básicas del request
        if (cuentaOrigenId == null || cuentaOrigenId.isBlank()
                || cuentaDestinoId == null || cuentaDestinoId.isBlank()) {
            throw new InvalidTransactionDataException(
                    "Las cuentas origen y destino no pueden ser nulas ni vacías");
        }

        if (monto == null || monto.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidTransactionDataException("El monto debe ser mayor que 0");
        }

        if (tipo == null || tipo.isBlank()) {
            tipo = "TRANSFERENCIA";
        }

        // 2) Cargar cuentas reales
        Cuenta cuentaOrigen = cuentaRepositoryPort.findById(cuentaOrigenId)
                .orElseThrow(() -> new CuentaNotFoundException(cuentaOrigenId));

        Cuenta cuentaDestino = cuentaRepositoryPort.findById(cuentaDestinoId)
                .orElseThrow(() -> new CuentaNotFoundException(cuentaDestinoId));

        // 3) Validar saldo suficiente (monto + comisión fija)
        BigDecimal totalDebito = monto.add(COMISION_FIJA);
        if (cuentaOrigen.getSaldo().compareTo(totalDebito) < 0) {
            throw new InvalidTransactionDataException("Saldo insuficiente en cuenta origen");
        }

        // 4) Construir la Transacción FINAL (aún no se toca saldos)
        Transaccion transaccion = Transaccion.builder()
                .transaccion_id(UUID.randomUUID().toString())
                .cuenta_origen_id(cuentaOrigenId)
                .cuenta_destino_id(cuentaDestinoId)
                .monto(monto)
                .comision(COMISION_FIJA)
                .tipo(tipo)
                .estado("COMPLETADA")
                .descripcion(descripcion)
                .fecha_creacion(LocalDateTime.now())
                .build();

        // 5) Validar invariantes de dominio
        transaccion.validarTransaccionInput();  // si falla, aún NO movimos saldos

        // 6) Ahora sí: actualizar saldos
        cuentaOrigen.setSaldo(cuentaOrigen.getSaldo().subtract(totalDebito));


        BigDecimal saldoDestinoActual =
                cuentaDestino.getSaldo() == null ? BigDecimal.ZERO : cuentaDestino.getSaldo();

        cuentaDestino.setSaldo(saldoDestinoActual.add(monto));
        cuentaOrigen.setFecha_actualizacion(LocalDateTime.now());
        cuentaDestino.setFecha_actualizacion(LocalDateTime.now());

        cuentaRepositoryPort.save(cuentaOrigen);
        cuentaRepositoryPort.save(cuentaDestino);

        // 7) Guardar transacción
        Transaccion guardada = transaccionRepositoryPort.save(transaccion);

        // 8) Notificar
        if (cuentaOrigen.getCliente_id() != null) {
            notificacionRepositoryPort.notificar(
                    cuentaOrigen.getCliente_id(),
                    "Has enviado " + monto + " a la cuenta " + cuentaDestinoId +
                            " (se cobró comisión fija de " + COMISION_FIJA + ")"
            );
        }

        if (cuentaDestino.getCliente_id() != null) {
            notificacionRepositoryPort.notificar(
                    cuentaDestino.getCliente_id(),
                    "Has recibido " + monto + " desde la cuenta " + cuentaOrigenId
            );
        }


        return guardada;


    }
}
