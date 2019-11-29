package com.bba.contacts;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.ImmutableList;

@RestController
@RequestMapping("/v2/contacts")
public class ContactsController {

	@GetMapping
	public ResponseEntity<List<ContactDto>> getList() {

		return ResponseEntity.ok(ImmutableList.of(ContactDto.builder().name("test").phone("303-123-1234").build()));
	}

}
