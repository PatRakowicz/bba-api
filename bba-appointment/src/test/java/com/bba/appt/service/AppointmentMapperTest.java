package com.bba.appt.service;

import com.bba.appt.AppointmentDto;
import com.bba.domain.AppointmentEntity;
import org.junit.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

public class AppointmentMapperTest {

    private AppointmentMapper mapper = Mappers.getMapper(AppointmentMapper.class);

    @Test
    public void mapDto() {
        AppointmentEntity entity = AppointmentEntity.builder()
            .id(1)
            .accountId(11)
            .clientId(22)
            .confirmed("N")
            .length("120")
            .name("Client S (303) 123-1234")
            .note("hc")
            .startTime(LocalDateTime.now())
            .subject("subject") // null in current impl
            .build();

        AppointmentDto dto = mapper.mapDto(entity);

        assertEquals(entity.getId(), dto.getId());
        assertEquals(entity.getClientId(), dto.getClient().getId());
        assertEquals(entity.getConfirmed(), dto.getConfirmed());
        assertEquals(entity.getLength(), String.valueOf(dto.getLength()));
        assertEquals(entity.getName(), dto.getName());
        assertEquals(entity.getNote(), dto.getNote());
        assertEquals(entity.getStartTime(), dto.getStartTime());
        assertEquals(entity.getSubject(), dto.getSubject());
    }

    @Test
    public void mapEntity() {
        AppointmentDto dto = AppointmentDto.builder()
            .id(1)
            .client(AppointmentDto.ClientDto.builder()
                .id(11)
                .name("name")
                .phone("3031231234")
                .build())
            .confirmed("N")
            .length(120)
            .name("Client S (303) 123-1234")
            .note("hc")
            .startTime(LocalDateTime.now())
            .subject("subject") // null in current impl
            .build();

        AppointmentEntity entity = mapper.mapEntity(dto);

        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getClient().getId(), entity.getClientId());
        assertEquals(dto.getConfirmed(), entity.getConfirmed());
        assertEquals(dto.getLength(), Integer.valueOf(entity.getLength()));
        assertEquals(dto.getName(), entity.getName());
        assertEquals(dto.getNote(), entity.getNote());
        assertEquals(dto.getStartTime(), entity.getStartTime());
        assertEquals(dto.getSubject(), entity.getSubject());
    }

    @Test
    public void mapExisting() {
        AppointmentDto dto = AppointmentDto.builder()
            .id(1)
            .client(AppointmentDto.ClientDto.builder()
                .id(11)
                .name("name")
                .phone("3031231234")
                .build())
            .confirmed("N")
            .length(120)
            .name("Client S (303) 123-1234")
            .note("hc")
            .startTime(LocalDateTime.now())
            .subject("subject") // null in current impl
            .build();

        AppointmentEntity existing = AppointmentEntity.builder().accountId(22).build();
        AppointmentEntity entity = mapper.mapExisting(dto, existing);

        assertEquals(existing.getAccountId(), entity.getAccountId());
        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getClient().getId(), entity.getClientId());
        assertEquals(dto.getConfirmed(), entity.getConfirmed());
        assertEquals(dto.getLength(), Integer.valueOf(entity.getLength()));
        assertEquals(dto.getName(), entity.getName());
        assertEquals(dto.getNote(), entity.getNote());
        assertEquals(dto.getStartTime(), entity.getStartTime());
        assertEquals(dto.getSubject(), entity.getSubject());
    }
}