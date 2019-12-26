package com.bba.appt.repositories;

import com.bba.domain.AppointmentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends CrudRepository<AppointmentEntity, Integer> {

    List<AppointmentEntity> findAllByAccountIdAndStartTimeBetween(Integer accountId, LocalDateTime startTime, LocalDateTime endTime);

    Optional<AppointmentEntity> findByIdAndAccountId(Integer appointmentId, Integer accountId);
}
