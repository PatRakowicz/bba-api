package com.bba.client.service;

import com.bba.client.ClientDto;
import com.bba.domain.ClientEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

@Mapper
public interface ClientMapper {

    @Mappings({
        @Mapping(target = "lastName", source = "lname"),
        @Mapping(target = "homePhone", source = "hphone"),
        @Mapping(target = "workPhone", source = "wphone")
    })
    ClientDto mapDto(ClientEntity entity);

    @Mappings({
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "accountId", ignore = true),
        @Mapping(target = "addressId", ignore = true),
        @Mapping(target = "xrefid", ignore = true),
        @Mapping(target = "picUrl", ignore = true),
        @Mapping(target = "lname", source = "lastName"),
        @Mapping(target = "hphone", source = "homePhone"),
        @Mapping(target = "wphone", source = "workPhone")
    })
    ClientEntity mapEntity(ClientDto dto);

    @Mappings({
        @Mapping(target = "id", expression = "java(entity.getId())"),
        @Mapping(target = "accountId", expression = "java(entity.getAccountId())"),
        @Mapping(target = "created", expression = "java(entity.getCreated())"),
        @Mapping(target = "xrefid", expression = "java(entity.getXrefid())"),
        @Mapping(target = "addressId", expression = "java(entity.getAddressId())"),
        @Mapping(target = "status", expression = "java(entity.getStatus())"),
        @Mapping(target = "picUrl", expression = "java(entity.getPicUrl())"),
        @Mapping(target = "lname", source = "lastName"),
        @Mapping(target = "hphone", source = "homePhone"),
        @Mapping(target = "wphone", source = "workPhone")
    })
    ClientEntity mapExisting(ClientDto dto, @MappingTarget ClientEntity entity);
}
