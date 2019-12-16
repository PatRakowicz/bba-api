package com.bba.appt;

import com.google.common.collect.ImmutableList;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v2/appts")
public class AppointmentController {

    @GetMapping
    public List<AppointmentDto> list() {
        return ImmutableList.of(AppointmentDto.builder().id(10).name("name").build());
    }

    @GetMapping("/{id}")
    public AppointmentDto get(@PathVariable("id") Integer clientId) {
        return AppointmentDto.builder().id(clientId).name("name").build();
    }

    @PostMapping
    public AppointmentDto save(@RequestBody AppointmentDto client) {
        return client;
    }

    @PutMapping
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void update(@RequestBody AppointmentDto client) {
        // TODO
    }
}
