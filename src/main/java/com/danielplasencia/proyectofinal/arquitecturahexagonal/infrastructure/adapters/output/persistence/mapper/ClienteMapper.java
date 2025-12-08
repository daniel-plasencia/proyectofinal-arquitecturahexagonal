package com.danielplasencia.proyectofinal.arquitecturahexagonal.infrastructure.adapters.output.persistence.mapper;


import com.danielplasencia.proyectofinal.arquitecturahexagonal.domain.model.Cliente;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.domain.model.Cuenta;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.infrastructure.adapters.input.rest.dto.ClienteRequest;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.infrastructure.adapters.input.rest.dto.ClienteResponse;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.infrastructure.adapters.output.persistence.entity.ClienteEntity;
import com.danielplasencia.proyectofinal.arquitecturahexagonal.infrastructure.adapters.output.persistence.entity.CuentaEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    // =========================
    // 1) Request → Domain
    // =========================
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "nombre", source = "nombre")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "documento", source = "documento")
    Cliente toDomain(ClienteRequest request);


    // =========================
    // 2) Entity → Domain
    // =========================
    @Mapping(target = "id", source = "id")
    @Mapping(target = "nombre", source = "nombre")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "documento", source = "documento")
    @Mapping(target = "fecha_creacion", source = "fechaCreacion")
    Cliente toDomain(ClienteEntity entity);

    List<Cliente> toDomain(List<ClienteEntity> entities);


    // =========================
    // 3) Domain → Entity
    // =========================
    @Mapping(target = "id", source = "id")
    @Mapping(target = "nombre", source = "nombre")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "documento", source = "documento")
    @Mapping(target = "fechaCreacion", ignore = true)  // lo genera BD
    ClienteEntity toEntity(Cliente domain);


    // =========================
    // 4) Domain → Response
    // =========================
    @Mapping(target = "id", source = "id")
    @Mapping(target = "nombre", source = "nombre")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "documento", source = "documento")
    @Mapping(target = "fechaCreacion", source = "fecha_creacion")
    ClienteResponse toResponse(Cliente domain);

    List<ClienteResponse> toResponse(List<Cliente> clientes);

}


