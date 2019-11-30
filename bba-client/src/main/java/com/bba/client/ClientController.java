package com.bba.client;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.ImmutableList;

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
