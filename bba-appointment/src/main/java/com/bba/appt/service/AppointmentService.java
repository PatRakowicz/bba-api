package com.bba.appt.service;

import com.bba.appt.AppointmentDto;
import com.bba.appt.repositories.AppointmentRepository;
import com.bba.appt.repositories.ClientRepository;
import com.bba.domain.AppointmentEntity;
import com.bba.domain.ClientEntity;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final ClientRepository clientRepository;
    private final AppointmentMapper mapper;

    public AppointmentService(AppointmentRepository appointmentRepository, ClientRepository clientRepository) {
        this.appointmentRepository = appointmentRepository;
        this.clientRepository = clientRepository;
        this.mapper = Mappers.getMapper(AppointmentMapper.class);
    }

    public List<AppointmentDto> getList(Integer accountId, LocalDate dateFrom, LocalDate dateTo) {
        LocalDateTime start = LocalDateTime.of(dateFrom, LocalTime.of(0, 0));
        LocalDateTime end = LocalDateTime.of(dateTo, LocalTime.of(0, 0));
        List<AppointmentEntity> list = appointmentRepository.findAllByAccountIdAndStartTimeBetween(accountId, start, end);
        return list.stream().map(mapper::mapDto).collect(Collectors.toList());
    }

    public AppointmentDto getAppointment(Integer accountId, Integer appointmentId) {
        AppointmentEntity entity = findAppointment(accountId, appointmentId);
        return mapper.mapDto(entity);
    }

    @Transactional
    public AppointmentDto save(Integer accountId, AppointmentDto dto) {
        AppointmentEntity entity = mapper.mapEntity(dto);
        ClientEntity clientEntity = findClient(accountId, dto.getClient().getId());
        entity.setAccountId(accountId);
        entity.setClientId(clientEntity.getId());
        // TODO - format appt-.anme from the client
        entity = appointmentRepository.save(entity);
        return mapper.mapDto(entity);
    }

    @Transactional
    public AppointmentDto update(Integer accountId, AppointmentDto dto) {
        AppointmentEntity existing = findAppointment(accountId, dto.getId());
        ClientEntity clientEntity = findClient(accountId, dto.getClient().getId());
        AppointmentEntity entity = mapper.mapExisting(dto, existing);
        entity.setClientId(clientEntity.getId());
        // TODO - format appt-.anme from the client
        entity = appointmentRepository.save(entity);
        return mapper.mapDto(entity);
    }

    @Transactional
    public void delete(Integer accountId, Integer appointmentId) {
        AppointmentEntity entity = findAppointment(accountId, appointmentId);
        appointmentRepository.delete(entity);
    }

    private AppointmentEntity findAppointment(Integer accountId, Integer appointmentId) {
        return appointmentRepository.findByIdAndAccountId(accountId, appointmentId).orElseThrow(
            () -> new RuntimeException("Appointment NOT FOUND: acct=" + accountId + ", appt=" + appointmentId));
    }

    private ClientEntity findClient(Integer accountId, Integer clientId) {
        return clientRepository.findByIdAndAccountId(clientId, accountId).orElseThrow(
            () -> new RuntimeException("Client NOT FOUND: acct=" + accountId + ", client=" + clientId));
    }
}
