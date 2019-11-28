package com.bba.contacts;

import java.util.Collections;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v2/contacts")
public class ContactsController {

	@GetMapping
	public ResponseEntity<List<Object>> getList() {
		
		return ResponseEntity.ok(Collections.emptyList());
	}
	
}
