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
    public List<AppointmentDto> list() {
        return service.getList(100, LocalDate.now(), LocalDate.now().plusDays(1));
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
