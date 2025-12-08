package com.danielplasencia.proyectofinal.arquitecturahexagonal.infrastructure.adapters.input.rest.controller;

import com.danielplasencia.proyectofinal.arquitecturahexagonal.application.ports.input.EncontrarClienteUseCase;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.domain.excepciones.ClienteNotFoundException;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.domain.excepciones.InvalidClienteDataException;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.domain.model.Cliente;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.infrastructure.adapters.input.rest.dto.ClienteResponse;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.infrastructure.adapters.output.persistence.mapper.ClienteMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Slf4j
public class ClienteController {

    private final EncontrarClienteUseCase encontrarClienteUseCase;

    private final ClienteMapper clienteMapper;

    // GET /clientes/{id}
    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponse> getClienteById(@PathVariable String id) {
        try {
            log.info("Buscando cliente con id {}", id);
            Cliente cliente = this.encontrarClienteUseCase.findClienteById(id);
            log.info("Cliente encontrado: {}", cliente.getNombre());
            return ResponseEntity.ok(this.clienteMapper.toResponse(cliente));
        } catch (InvalidClienteDataException e) {
            log.warn("Invalid cliente ID: {}", id);
            return ResponseEntity.badRequest().build();
        } catch (ClienteNotFoundException e) {
            log.warn("Cliente no encontrado: {}", id);
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("Error inesperado al buscar cliente {}", id, e);
            return ResponseEntity.internalServerError().build();
        }
    }

    // GET /clientes
    @GetMapping
    public ResponseEntity<List<ClienteResponse>> getAllClientes() {
        try {
            log.info("Listando todos los clientes");
            List<Cliente> clientes = this.encontrarClienteUseCase.findAllCliente();
            List<ClienteResponse> responses = this.clienteMapper.toResponse(clientes);
            log.info("Encontre {} clientes", responses.size());
            return ResponseEntity.ok(responses);
        } catch (Exception e) {
            log.error("Error inesperado al listar clientes", e);
            return ResponseEntity.internalServerError().build();
        }
    }

}
