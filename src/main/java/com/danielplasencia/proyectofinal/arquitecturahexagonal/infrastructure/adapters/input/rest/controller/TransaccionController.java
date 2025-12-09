package com.danielplasencia.proyectofinal.arquitecturahexagonal.infrastructure.adapters.input.rest.controller;

import com.danielplasencia.proyectofinal.arquitecturahexagonal.application.ports.input.*;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.domain.excepciones.InvalidTransactionDataException;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.domain.excepciones.TransaccionNotFoundException;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.domain.model.Transaccion;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.infrastructure.adapters.input.rest.dto.TransaccionRequest;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.infrastructure.adapters.input.rest.dto.TransaccionResponse;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.infrastructure.adapters.output.persistence.mapper.TransaccionMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transacciones")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Slf4j
public class TransaccionController {

    private final TransferirDineroUseCase transferirDineroUseCase;
    private final EncontrarTransaccionUseCase encontrarTansaccionUseCase;
    private final TransaccionMapper transaccionMapper;

    // =====================================================
    // POST /transacciones
    // Realiza una transferencia (cuenta_origen -> cuenta_destino)
    // =====================================================
    @PostMapping
    public ResponseEntity<TransaccionResponse> transferir(@RequestBody TransaccionRequest request) {
        try {
            log.info("Iniciando transferencia: origen={} destino={} monto={}",
                    request.getCuentaOrigenId(), request.getCuentaDestinoId(), request.getMonto());

            Transaccion transaccionDomain = this.transaccionMapper.toDomain(request);
            Transaccion realizada = this.transferirDineroUseCase.transfer(transaccionDomain);
            TransaccionResponse response = this.transaccionMapper.toResponse(realizada);

            log.info("Transferencia completada con id={}", response.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (InvalidTransactionDataException e) {
            log.warn("Datos inválidos en transferencia: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            log.error("Error inesperado al realizar transferencia", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    // =====================================================
    // GET /transacciones/{id}
    // Obtiene una transacción por su ID
    // =====================================================
    @GetMapping("/{id}")
    public ResponseEntity<TransaccionResponse> getTransaccionById(@PathVariable String id) {
        try {
            log.info("Buscando transacción con id {}", id);
            Transaccion transaccion = this.encontrarTansaccionUseCase.findTransaccionById(id);
            TransaccionResponse response = this.transaccionMapper.toResponse(transaccion);
            return ResponseEntity.ok(response);

        } catch (IllegalArgumentException e) {
            log.warn("ID de transacción inválido: {}", id);
            return ResponseEntity.badRequest().build();
        } catch (TransaccionNotFoundException e) {
            log.warn("Transacción no encontrada: {}", id);
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("Error inesperado al buscar transacción {}", id, e);
            return ResponseEntity.internalServerError().build();
        }
    }

    // =====================================================
    // GET /transacciones
    // Lista todas las transacciones
    // =====================================================
    @GetMapping
    public ResponseEntity<List<TransaccionResponse>> getAllTransacciones() {
        try {
            log.info("Listando todas las transacciones");
            List<Transaccion> list = this.encontrarTansaccionUseCase.findAllTransaccion();
            List<TransaccionResponse> responses = this.transaccionMapper.toResponse(list);
            log.info("Se encontraron {} transacciones", responses.size());
            return ResponseEntity.ok(responses);

        } catch (Exception e) {
            log.error("Error inesperado al listar transacciones", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    // =====================================================
    // GET /transacciones/por-origen/{cuentaId}
    // Transacciones donde cuentaId es cuenta_origen
    // =====================================================
    @GetMapping("/por-origen/{cuentaId}")
    public ResponseEntity<List<TransaccionResponse>> getByCuentaOrigen(@PathVariable String cuentaId) {
        try {
            log.info("Buscando transacciones con cuenta_origen_id={}", cuentaId);
            List<Transaccion> list =
                    this.encontrarTansaccionUseCase.findByCuentaOrigen(cuentaId);

            List<TransaccionResponse> responses =
                    this.transaccionMapper.toResponse(list);

            log.info("Se encontraron {} transacciones con cuenta_origen_id={}",
                    responses.size(), cuentaId);
            return ResponseEntity.ok(responses);

        } catch (IllegalArgumentException e) {
            log.warn("cuentaId origen inválido: {}", cuentaId);
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            log.error("Error inesperado al buscar transacciones por cuenta origen {}", cuentaId, e);
            return ResponseEntity.internalServerError().build();
        }
    }

    // =====================================================
    // GET /transacciones/por-destino/{cuentaId}
    // Transacciones donde cuentaId es cuenta_destino
    // =====================================================
    @GetMapping("/por-destino/{cuentaId}")
    public ResponseEntity<List<TransaccionResponse>> getByCuentaDestino(@PathVariable String cuentaId) {
        try {
            log.info("Buscando transacciones con cuenta_destino_id={}", cuentaId);
            List<Transaccion> list =
                    this.encontrarTansaccionUseCase.findByCuentaDestino(cuentaId);

            List<TransaccionResponse> responses =
                    this.transaccionMapper.toResponse(list);

            log.info("Se encontraron {} transacciones con cuenta_destino_id={}",
                    responses.size(), cuentaId);
            return ResponseEntity.ok(responses);

        } catch (IllegalArgumentException e) {
            log.warn("cuentaId destino inválido: {}", cuentaId);
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            log.error("Error inesperado al buscar transacciones por cuenta destino {}", cuentaId, e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
