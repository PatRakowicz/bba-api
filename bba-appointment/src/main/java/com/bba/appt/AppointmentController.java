package com.bba.appt;

import com.bba.appt.service.AppointmentService;
import com.google.common.collect.ImmutableList;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v2/appts")
public class AppointmentController {

    private final AppointmentService service;

    public AppointmentController(AppointmentService service) {
        this.service = service;
    }

    @GetMapping
    public AppointmentListDto list() {
        return list("w", LocalDate.now());
    }

    @GetMapping("/{period}/{date}")
    public AppointmentListDto list(
        @PathVariable(name = "period") String period,
        @PathVariable(name = "date", required = false) LocalDate date) {
        DatePeriodDto dates = DatePeriodUtil.calculateDates(period, date, false);
        return AppointmentListDto.builder()
            .datePeriod(dates)
            .result(service.getList(100, dates.getStart(), dates.getEnd()))
            .build();
    }

    @GetMapping("/{id}")
    public AppointmentDto get(@PathVariable("id") Integer appointmentId) {
        return service.getAppointment(appointmentId, 100);
    }

    @PostMapping
    public AppointmentDto save(@RequestBody AppointmentDto appointmentDto) {
        return service.save(appointmentDto, 100);
    }

    @PutMapping
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void update(@RequestBody AppointmentDto appointmentDto) {
        service.update(appointmentDto, 100);
    }

    @DeleteMapping
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@RequestBody AppointmentDto appointmentDto) {
        service.delete(appointmentDto.getId(), 100);
    }
}
