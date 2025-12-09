package com.danielplasencia.proyectofinal.arquitecturahexagonal.infrastructure.adapters.input.rest.controller;

import com.danielplasencia.proyectofinal.arquitecturahexagonal.application.ports.input.ConsultarSaldoUseCase;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.application.ports.input.CrearCuentaUseCase;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.application.ports.input.EncontrarClienteUseCase;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.application.ports.input.EncontrarCuentaUseCase;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.domain.excepciones.ClienteNotFoundException;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.domain.excepciones.CuentaNotFoundException;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.domain.excepciones.InvalidClienteDataException;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.domain.excepciones.InvalidCuentaDataException;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.domain.model.Cliente;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.domain.model.Cuenta;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.infrastructure.adapters.input.rest.dto.ClienteResponse;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.infrastructure.adapters.input.rest.dto.CuentaRequest;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.infrastructure.adapters.input.rest.dto.CuentaResponse;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.infrastructure.adapters.output.persistence.mapper.ClienteMapper;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.infrastructure.adapters.output.persistence.mapper.CuentaMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/cuentas")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Slf4j
public class CuentaController {

    private final CrearCuentaUseCase crearCuentaUseCase;
    private final EncontrarCuentaUseCase encontrarCuentaUseCase;
    private final ConsultarSaldoUseCase consultarSaldoUseCase;
    private final CuentaMapper cuentaMapper;

    // ==========================
    // POST /cuentas
    // ==========================
    @PostMapping
    public ResponseEntity<CuentaResponse> crearCuenta(@RequestBody CuentaRequest request) {
        try {
            log.info("Creando cuenta para clienteId={} numeroCuenta={}",
                    request.getClienteId(), request.getNumeroCuenta());

            Cuenta cuentaDomain = this.cuentaMapper.toDomain(request);
            Cuenta cuentaCreada = this.crearCuentaUseCase.execute(cuentaDomain);

            if (cuentaCreada == null) {
                log.warn("Cuenta service returned null");
                return ResponseEntity.badRequest().build();
            }

            CuentaResponse response = this.cuentaMapper.toResponse(cuentaCreada);
            log.info("Cuenta creada con id={}", response.getId());

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (InvalidCuentaDataException e) {
            log.warn("Datos inválidos al crear cuenta: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            log.error("Error inesperado al crear cuenta", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    // ==========================
    // GET /cuentas/{id}
    // ==========================
    @GetMapping("/{id}")
    public ResponseEntity<CuentaResponse> getCuentaById(@PathVariable String id) {
        try {
            log.info("Buscando cuenta con id {}", id);
            Cuenta cuenta = this.encontrarCuentaUseCase.findCuentaById(id);
            CuentaResponse response = this.cuentaMapper.toResponse(cuenta);
            log.info("Cuenta encontrada: numero={}", response.getNumeroCuenta());
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException | InvalidCuentaDataException e) {
            log.warn("ID de cuenta inválido: {}", id);
            return ResponseEntity.badRequest().build();
        } catch (CuentaNotFoundException e) {
            log.warn("Cuenta no encontrada: {}", id);
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("Error inesperado al buscar cuenta {}", id, e);
            return ResponseEntity.internalServerError().build();
        }
    }

    // ==========================
    // GET /cuentas
    // ==========================
    @GetMapping
    public ResponseEntity<List<CuentaResponse>> getAllCuentas() {
        try {
            log.info("Listando todas las cuentas");
            List<Cuenta> cuentas = this.encontrarCuentaUseCase.findAllCuenta();
            List<CuentaResponse> responses = this.cuentaMapper.toResponse(cuentas);
            log.info("Se encontraron {} cuentas", responses.size());
            return ResponseEntity.ok(responses);
        } catch (Exception e) {
            log.error("Error inesperado al listar cuentas", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    // ==========================
    // GET /cuentas/por-cliente/{clienteId}
    // Buscar por client_id
    // ==========================
    @GetMapping("/por-cliente/{clienteId}")
    public ResponseEntity<List<CuentaResponse>> getCuentasPorCliente(@PathVariable String clienteId) {
        try {
            log.info("Buscando cuentas para clienteId={}", clienteId);
            List<Cuenta> cuentas = this.encontrarCuentaUseCase.findByClienteId(clienteId);
            List<CuentaResponse> responses = this.cuentaMapper.toResponse(cuentas);
            log.info("Se encontraron {} cuentas para clienteId={}", responses.size(), clienteId);
            return ResponseEntity.ok(responses);
        } catch (IllegalArgumentException e) {
            log.warn("clienteId inválido: {}", clienteId);
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            log.error("Error inesperado al buscar cuentas por clienteId={}", clienteId, e);
            return ResponseEntity.internalServerError().build();
        }
    }

    // ==========================
    // GET /cuentas/{id}/saldo
    // ==========================
    @GetMapping("/{id}/saldo")
    public ResponseEntity<BigDecimal> getSaldo(@PathVariable String id) {
        try {
            log.info("Consultando saldo de la cuenta {}", id);
            BigDecimal saldo = this.consultarSaldoUseCase.consultarSaldo(id);
            return ResponseEntity.ok(saldo);
        } catch (IllegalArgumentException e) {
            log.warn("ID de cuenta inválido al consultar saldo: {}", id);
            return ResponseEntity.badRequest().build();
        } catch (CuentaNotFoundException e) {
            log.warn("Cuenta no encontrada al consultar saldo: {}", id);
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("Error inesperado al consultar saldo de la cuenta {}", id, e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
