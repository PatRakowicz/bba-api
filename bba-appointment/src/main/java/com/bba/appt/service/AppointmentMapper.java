package com.bba.appt.service;

import com.bba.appt.AppointmentDto;
import com.bba.domain.AppointmentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

@Mapper
public interface AppointmentMapper {

    @Mappings({
        @Mapping(target = "client.id", source = "clientId"),
        @Mapping(target = "client.name", ignore = true),
        @Mapping(target = "client.phone", ignore = true)
    })
    AppointmentDto mapDto(AppointmentEntity entity);

    @Mappings({
        @Mapping(target = "clientId", source = "client.id"),
        @Mapping(target = "accountId", ignore = true)
    })
    AppointmentEntity mapEntity(AppointmentDto dto);

    @Mappings({
        @Mapping(target = "accountId", expression = "java(entity.getAccountId())"),
        @Mapping(target = "clientId", source = "client.id")
    })
    AppointmentEntity mapExisting(AppointmentDto dto, @MappingTarget AppointmentEntity entity);
}
