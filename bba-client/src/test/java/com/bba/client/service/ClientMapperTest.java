package com.bba.client.service;

import com.bba.client.ClientDto;
import org.junit.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ClientMapperTest {

    private ClientMapper mapper = Mappers.getMapper(ClientMapper.class);

    @Test
    public void testMapDto() {
        ClientEntity entity = ClientEntity.builder()
            .id(1)
            .accountId(2)
            .addressId(3)
            .name("name")
            .lname("lname")
            .birthdate("2019-01-01")
            .created(LocalDateTime.now())
            .email("test@test.com")
            .phone("303-123-1234")
            .wphone("303-123-1234")
            .hphone("303-123-1234")
            .status("A")
            .gender("M")
            .family("family")
            .occupation("occupation")
            .picUrl("picUrl")
            .xrefid("xrefid")
            .build();
        ClientDto dto = mapper.mapDto(entity);

        assertEquals(entity.getId(), dto.getId());
        assertEquals(entity.getName(), dto.getName());
        assertEquals(entity.getLname(), dto.getLastName());
        assertEquals(entity.getBirthdate(), dto.getBirthdate().format(DateTimeFormatter.ISO_DATE));
        assertEquals(entity.getCreated(), dto.getCreated());
        assertEquals(entity.getEmail(), dto.getEmail());
        assertEquals(entity.getPhone(), dto.getPhone());
        assertEquals(entity.getWphone(), dto.getWorkPhone());
        assertEquals(entity.getHphone(), dto.getHomePhone());
        assertEquals(entity.getStatus(), dto.getStatus());
        assertEquals(entity.getGender(), dto.getGender());
        assertEquals(entity.getFamily(), dto.getFamily());
        assertEquals(entity.getOccupation(), dto.getOccupation());
    }

    @Test
    public void testMapEntity() {
        ClientDto dto = ClientDto.builder()
            .id(1)
            .name("name")
            .lastName("lname")
            .birthdate(LocalDate.of(2019, 1, 1))
            .created(LocalDateTime.now())
            .email("test@test.com")
            .phone("303-123-1234")
            .workPhone("303-123-1234")
            .homePhone("303-123-1234")
            .status("A")
            .gender("M")
            .family("family")
            .occupation("occupation")
            .build();
        ClientEntity entity = mapper.mapEntity(dto);

        assertNull(entity.getId());
        assertNull(entity.getAccountId());
        assertNull(entity.getAddressId());
        assertNull(entity.getXrefid());
        assertNull(entity.getPicUrl());
        assertEquals(dto.getName(), entity.getName());
        assertEquals(dto.getLastName(), entity.getLname());
        assertEquals(dto.getBirthdate().format(DateTimeFormatter.ISO_DATE), entity.getBirthdate());
        assertEquals(dto.getCreated(), entity.getCreated());
        assertEquals(dto.getEmail(), entity.getEmail());
        assertEquals(dto.getPhone(), entity.getPhone());
        assertEquals(dto.getWorkPhone(), entity.getWphone());
        assertEquals(dto.getHomePhone(), entity.getHphone());
        assertEquals(dto.getStatus(), entity.getStatus());
        assertEquals(dto.getGender(), entity.getGender());
        assertEquals(dto.getFamily(), entity.getFamily());
        assertEquals(dto.getOccupation(), entity.getOccupation());
    }

    @Test
    public void testMapExisting() {
        ClientEntity existing = ClientEntity.builder()
            .id(1)
            .accountId(2)
            .addressId(3)
            .name("name")
            .lname("lname")
            .birthdate("2019-01-01")
            .created(LocalDateTime.now())
            .email("test@test.com")
            .phone("303-123-1234")
            .wphone("303-123-1234")
            .hphone("303-123-1234")
            .status("A")
            .gender("M")
            .family("family")
            .occupation("occupation")
            .picUrl("picUrl")
            .xrefid("xrefid")
            .build();
        ClientDto dto = ClientDto.builder()
            .id(1)
            .name("name")
            .lastName("lname")
            .birthdate(LocalDate.of(2019, 1, 1))
            .created(LocalDateTime.now())
            .email("test@test.com")
            .phone("303-123-1234")
            .workPhone("303-123-1234")
            .homePhone("303-123-1234")
            .status("A")
            .gender("M")
            .family("family")
            .occupation("occupation")
            .build();
        ClientEntity entity = mapper.mapExisting(dto, existing);

        assertEquals(existing.getId(), entity.getId());
        assertEquals(existing.getAccountId(), entity.getAccountId());
        assertEquals(existing.getAddressId(), entity.getAddressId());
        assertEquals(existing.getXrefid(), entity.getXrefid());
        assertEquals(existing.getPicUrl(), entity.getPicUrl());
        assertEquals(existing.getCreated(), entity.getCreated());

        assertEquals(dto.getName(), entity.getName());
        assertEquals(dto.getLastName(), entity.getLname());
        assertEquals(dto.getBirthdate().format(DateTimeFormatter.ISO_DATE), entity.getBirthdate());
        assertEquals(dto.getEmail(), entity.getEmail());
        assertEquals(dto.getPhone(), entity.getPhone());
        assertEquals(dto.getWorkPhone(), entity.getWphone());
        assertEquals(dto.getHomePhone(), entity.getHphone());
        assertEquals(dto.getStatus(), entity.getStatus());
        assertEquals(dto.getGender(), entity.getGender());
        assertEquals(dto.getFamily(), entity.getFamily());
        assertEquals(dto.getOccupation(), entity.getOccupation());
    }

}
