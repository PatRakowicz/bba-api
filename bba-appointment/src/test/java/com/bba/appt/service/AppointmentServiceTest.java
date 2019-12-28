package com.bba.appt.service;

import com.bba.appt.AppointmentDto;
import com.bba.appt.repositories.AppointmentRepository;
import com.bba.appt.repositories.ApptClientRepository;
import com.bba.domain.AppointmentEntity;
import com.bba.domain.ClientEntity;
import com.google.common.collect.ImmutableList;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class AppointmentServiceTest {

    private static final int ACCT_ID = 1;
    private static final int CLIENT_ID = 11;
    private static final int APPT_ID = 100;

    @InjectMocks
    private AppointmentService service;

    @Mock
    private AppointmentRepository appointmentRepository;
    @Mock
    private ApptClientRepository apptClientRepository;

    @Captor
    private ArgumentCaptor<AppointmentEntity> captor;

    private Clock clock;

    private AppointmentEntity entity;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        clock = Clock.system(ZoneId.of("MST", ZoneId.SHORT_IDS));
        entity = AppointmentEntity.builder()
            .id(APPT_ID)
            .accountId(ACCT_ID)
            .subject("test")
            .name("name")
            .startTime(LocalDateTime.now(clock))
            .note("note")
            .length("120")
            .confirmed("N")
            .clientId(CLIENT_ID)
            .build();
        when(apptClientRepository.findByIdAndAccountId(CLIENT_ID, ACCT_ID)).thenReturn(
            Optional.of(ClientEntity.builder().id(CLIENT_ID).name("clientName").phone("303-123-1234").build()));
    }

    @Test
    public void testGetList() {
        LocalDate from = LocalDate.now(clock).minusDays(1);
        LocalDate to = LocalDate.now(clock).plusDays(1);
        when(appointmentRepository.findAllByAccountIdAndStartTimeBetween(eq(ACCT_ID), any(), any()))
            .thenReturn(ImmutableList.of(entity));

        List<AppointmentDto> list = service.getList(ACCT_ID, from, to);

        assertNotNull(list);
        assertEquals(1, list.size());
        assertEquals(APPT_ID, (int)list.get(0).getId());
        assertEquals("name", list.get(0).getName());
        verify(appointmentRepository).findAllByAccountIdAndStartTimeBetween(any(), any(), any());
        verifyNoMoreInteractions(appointmentRepository, apptClientRepository);
    }

    @Test
    public void testGetAppointment() {
        when(appointmentRepository.findByIdAndAccountId(APPT_ID, ACCT_ID))
            .thenReturn(Optional.of(entity));

        AppointmentDto dto = service.getAppointment(APPT_ID, ACCT_ID);

        assertNotNull(dto);
        assertEquals(APPT_ID, (int) dto.getId());
        assertEquals("name", dto.getName());
        verify(appointmentRepository).findByIdAndAccountId(any(), any());
        verifyNoMoreInteractions(appointmentRepository, apptClientRepository);
    }

    @Test
    public void testSave() {
        AppointmentDto dto = AppointmentDto.builder()
            .name("name")
            .cid(CLIENT_ID)
            .build();
        when(appointmentRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        AppointmentDto saved = service.save(dto, ACCT_ID);

        assertNotNull(saved);
        verify(appointmentRepository).save(captor.capture());
        AppointmentEntity savedEntity = captor.getValue();
        assertNotNull(savedEntity);
        assertEquals(ACCT_ID, (int) savedEntity.getAccountId());
        assertEquals(dto.getCid(), savedEntity.getClientId());
        assertEquals(dto.getName(), savedEntity.getName());
        assertEquals(saved.getName(), dto.getName());
        verify(apptClientRepository).findByIdAndAccountId(any(), any());
        verifyNoMoreInteractions(appointmentRepository, apptClientRepository);
    }

    @Test
    public void testUpdate() {
        AppointmentDto dto = AppointmentDto.builder()
            .id(APPT_ID)
            .name("nameUpdated")
            .len(240)
            .cid(CLIENT_ID)
            .build();
        when(appointmentRepository.findByIdAndAccountId(APPT_ID, ACCT_ID))
            .thenReturn(Optional.of(entity));
        when(appointmentRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        AppointmentDto updated = service.save( dto, ACCT_ID);

        assertNotNull(updated);
        verify(appointmentRepository).save(captor.capture());
        AppointmentEntity updatedEntity = captor.getValue();
        assertNotNull(updatedEntity);
        assertEquals(ACCT_ID, (int) updatedEntity.getAccountId());
        assertEquals(dto.getCid(), updatedEntity.getClientId());
        assertEquals(dto.getName(), updatedEntity.getName());
        assertEquals(dto.getLen(), Integer.valueOf(updatedEntity.getLength()));
        assertEquals(updated.getName(), dto.getName());
        verify(apptClientRepository).findByIdAndAccountId(any(), any());
        verifyNoMoreInteractions(appointmentRepository, apptClientRepository);
    }

    @Test
    public void testDelete() {
        when(appointmentRepository.findByIdAndAccountId(APPT_ID, ACCT_ID))
            .thenReturn(Optional.of(entity));

        service.delete(APPT_ID, ACCT_ID);

        verify(appointmentRepository).findByIdAndAccountId(any(), any());
        verify(appointmentRepository).delete(any());
        verifyNoMoreInteractions(appointmentRepository, apptClientRepository);
    }
}
