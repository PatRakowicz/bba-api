package com.bba.client;

import com.google.common.collect.ImmutableList;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v2/clients")
public class ClientController {

    @GetMapping
    public List<ClientDto> list() {

        return ImmutableList.of(ClientDto.builder().id(1).name("test").phone("303-123-1234").build());
    }

    @GetMapping("/{id}")
    public ClientDto get(@PathVariable("id") Long id) {
        return ClientDto.builder().id(1).name("test").phone("303-123-1234").build();
    }

    @PostMapping
    public ClientDto save(@RequestBody ClientDto client) {
        return client;
    }

    @PutMapping
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void update(@RequestBody ClientDto client) {

    }
}
