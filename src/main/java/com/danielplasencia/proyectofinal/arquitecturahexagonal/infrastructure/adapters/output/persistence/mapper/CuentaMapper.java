package com.danielplasencia.proyectofinal.arquitecturahexagonal.infrastructure.adapters.output.persistence.mapper;

import com.danielplasencia.proyectofinal.arquitecturahexagonal.domain.model.Cuenta;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.infrastructure.adapters.input.rest.dto.CuentaRequest;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.infrastructure.adapters.input.rest.dto.CuentaResponse;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.infrastructure.adapters.output.persistence.entity.CuentaEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CuentaMapper {

    // =========================
    // 1) Request → Domain
    // =========================
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "cliente_id", source = "clienteId")
    @Mapping(target = "numero_cuenta", source = "numeroCuenta")
    @Mapping(target = "saldo", source = "saldo")
    @Mapping(target = "estado", source = "estado")
    Cuenta toDomain(CuentaRequest request);


    // =========================
    // 2) Entity → Domain
    // =========================
    @Mapping(target = "cuenta_id", source = "cuentaId")
    @Mapping(target = "cliente_id", source = "clienteId")
    @Mapping(target = "numero_cuenta", source = "numeroCuenta")
    @Mapping(target = "saldo", source = "saldo")
    @Mapping(target = "estado", source = "estado")
    @Mapping(target = "fecha_creacion", source = "fechaCreacion")
    @Mapping(target = "fecha_actualizacion", source = "fechaActualizacion")
    Cuenta toDomain(CuentaEntity entity);

    List<Cuenta> toDomain(List<CuentaEntity> entities);


    // =========================
    // 3) Domain → Entity
    // =========================
    @Mapping(target = "cuentaId", source = "cuenta_id")
    @Mapping(target = "clienteId", source = "cliente_id")
    @Mapping(target = "numeroCuenta", source = "numero_cuenta")
    @Mapping(target = "saldo", source = "saldo")
    @Mapping(target = "estado", source = "estado")
    @Mapping(target = "fechaCreacion", ignore = true)       // BD lo genera
    @Mapping(target = "fechaActualizacion", ignore = true)  // BD lo actualiza
    CuentaEntity toEntity(Cuenta domain);


    // =========================
    // 4) Domain → Response
    // =========================
    @Mapping(target = "id", source = "cuenta_id")
    @Mapping(target = "clienteId", source = "cliente_id")
    @Mapping(target = "numeroCuenta", source = "numero_cuenta")
    @Mapping(target = "saldo", source = "saldo")
    @Mapping(target = "estado", source = "estado")
    @Mapping(target = "fechaCreacion", source = "fecha_creacion")
    @Mapping(target = "fechaActualizacion", source = "fecha_actualizacion")
    CuentaResponse toResponse(Cuenta domain);

    List<CuentaResponse> toResponse(List<Cuenta> cuentas);

}
