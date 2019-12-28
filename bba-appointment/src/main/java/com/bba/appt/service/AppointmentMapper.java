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
        @Mapping(target = "cid", source = "clientId"),
        @Mapping(target = "conf", source = "confirmed"),
        @Mapping(target = "len", source = "length"),
        @Mapping(target = "start", source = "startTime")
    })
    AppointmentDto mapDto(AppointmentEntity entity);

    @Mappings({
        @Mapping(target = "accountId", ignore = true),
        @Mapping(target = "clientId", source = "cid"),
        @Mapping(target = "confirmed", ignore = true),
        @Mapping(target = "length", source = "len"),
        @Mapping(target = "startTime", source = "start")
    })
    AppointmentEntity mapEntity(AppointmentDto dto);

    @Mappings({
        @Mapping(target = "accountId", expression = "java(entity.getAccountId())"),
        @Mapping(target = "clientId", source = "cid"),
        @Mapping(target = "confirmed", expression = "java(entity.getConfirmed())"),
        @Mapping(target = "length", source = "len"),
        @Mapping(target = "startTime", source = "start")
    })
    AppointmentEntity mapExisting(AppointmentDto dto, @MappingTarget AppointmentEntity entity);
}
