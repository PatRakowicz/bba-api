package com.bba.appt.service;

import com.bba.appt.AppointmentDto;
import com.bba.appt.repositories.AppointmentRepository;
import com.bba.appt.repositories.ApptClientRepository;
import com.bba.domain.AppointmentEntity;
import com.bba.domain.ClientEntity;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final ApptClientRepository apptClientRepository;
    private final AppointmentMapper mapper;

    public AppointmentService(AppointmentRepository appointmentRepository, ApptClientRepository apptClientRepository) {
        this.appointmentRepository = appointmentRepository;
        this.apptClientRepository = apptClientRepository;
        this.mapper = Mappers.getMapper(AppointmentMapper.class);
    }

    public List<AppointmentDto> getList(Integer accountId, LocalDate dateFrom, LocalDate dateTo) {
        LocalDateTime start = dateFrom.atStartOfDay();
        LocalDateTime end = dateTo.atTime(LocalTime.MAX);
        log.debug("list appts acct={} between {}-{}", accountId, start, end);
        List<AppointmentEntity> list = appointmentRepository.findAllByAccountIdAndStartTimeBetween(accountId, start, end);
        return list.stream().map(mapper::mapDto).collect(Collectors.toList());
    }

    public AppointmentDto getAppointment(Integer appointmentId, Integer accountId) {
        AppointmentEntity entity = findAppointment(appointmentId, accountId);
        return mapper.mapDto(entity);
    }

    @Transactional
    public AppointmentDto save(AppointmentDto dto, Integer accountId) {
        AppointmentEntity entity = mapper.mapEntity(dto);
        ClientEntity clientEntity = findClient(dto.getCid(), accountId);
        entity.setAccountId(accountId);
        entity.setClientId(clientEntity.getId());
        // TODO - format appt-.name from the client
        entity = appointmentRepository.save(entity);
        return mapper.mapDto(entity);
    }

    @Transactional
    public AppointmentDto update(AppointmentDto dto, Integer accountId) {
        AppointmentEntity existing = findAppointment(accountId, dto.getId());
        ClientEntity clientEntity = findClient(dto.getCid(), accountId);
        AppointmentEntity entity = mapper.mapExisting(dto, existing);
        entity.setClientId(clientEntity.getId());
        // TODO - format appt-.name from the client
        entity = appointmentRepository.save(entity);
        return mapper.mapDto(entity);
    }

    @Transactional
    public void delete(Integer appointmentId, Integer accountId) {
        AppointmentEntity entity = findAppointment(appointmentId, accountId);
        appointmentRepository.delete(entity);
    }

    private AppointmentEntity findAppointment(Integer appointmentId, Integer accountId) {
        return appointmentRepository.findByIdAndAccountId(appointmentId, accountId).orElseThrow(
            () -> new RuntimeException("Appointment NOT FOUND: appt=" + appointmentId + ", acct=" + accountId));
    }

    private ClientEntity findClient(Integer clientId, Integer accountId) {
        return apptClientRepository.findByIdAndAccountId(clientId, accountId).orElseThrow(
            () -> new RuntimeException("Client NOT FOUND: client=" + clientId + ", acct=" + accountId));
    }
}
