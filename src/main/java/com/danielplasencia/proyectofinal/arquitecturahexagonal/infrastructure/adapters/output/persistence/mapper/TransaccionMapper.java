package com.danielplasencia.proyectofinal.arquitecturahexagonal.infrastructure.adapters.output.persistence.mapper;

import com.danielplasencia.proyectofinal.arquitecturahexagonal.domain.model.Cuenta;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.domain.model.Transaccion;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.infrastructure.adapters.input.rest.dto.TransaccionRequest;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.infrastructure.adapters.input.rest.dto.TransaccionResponse;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.infrastructure.adapters.output.persistence.entity.CuentaEntity;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.infrastructure.adapters.output.persistence.entity.TransaccionEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransaccionMapper {

    // =========================
    // 1) Request → Domain
    // =========================
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "cuenta_origen_id", source = "cuentaOrigenId")
    @Mapping(target = "cuenta_destino_id", source = "cuentaDestinoId")
    @Mapping(target = "monto", source = "monto")
    @Mapping(target = "tipo", source = "tipo")
    @Mapping(target = "descripcion", source = "descripcion")
    Transaccion toDomain(TransaccionRequest request);


    // =========================
    // 2) Entity → Domain
    // =========================
    @Mapping(target = "transaccion_id", source = "transaccionId")
    @Mapping(target = "cuenta_origen_id", source = "cuentaOrigenId")
    @Mapping(target = "cuenta_destino_id", source = "cuentaDestinoId")
    @Mapping(target = "monto", source = "monto")
    @Mapping(target = "comision", source = "comision")
    @Mapping(target = "tipo", source = "tipo")
    @Mapping(target = "estado", source = "estado")
    @Mapping(target = "descripcion", source = "descripcion")
    @Mapping(target = "fecha_creacion", source = "fechaCreacion")
    Transaccion toDomain(TransaccionEntity entity);

    List<Transaccion> toDomain(List<TransaccionEntity> entities);


    // =========================
    // 3) Domain → Entity
    // =========================
    @Mapping(target = "transaccionId", source = "transaccion_id")
    @Mapping(target = "cuentaOrigenId", source = "cuenta_origen_id")
    @Mapping(target = "cuentaDestinoId", source = "cuenta_destino_id")
    @Mapping(target = "monto", source = "monto")
    @Mapping(target = "comision", source = "comision")
    @Mapping(target = "tipo", source = "tipo")
    @Mapping(target = "estado", source = "estado")
    @Mapping(target = "descripcion", source = "descripcion")
    @Mapping(target = "fechaCreacion", ignore = true) // BD lo genera
    TransaccionEntity toEntity(Transaccion domain);


    // =========================
    // 4) Domain → Response
    // =========================
    @Mapping(target = "id", source = "transaccion_id")
    @Mapping(target = "cuentaOrigenId", source = "cuenta_origen_id")
    @Mapping(target = "cuentaDestinoId", source = "cuenta_destino_id")
    @Mapping(target = "monto", source = "monto")
    @Mapping(target = "comision", source = "comision")
    @Mapping(target = "tipo", source = "tipo")
    @Mapping(target = "estado", source = "estado")
    @Mapping(target = "descripcion", source = "descripcion")
    @Mapping(target = "fechaCreacion", source = "fecha_creacion")
    TransaccionResponse toResponse(Transaccion domain);

    List<TransaccionResponse> toResponse(List<Transaccion> transacciones);

}
