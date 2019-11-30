package com.bba.client;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;

import static org.hamcrest.Matchers.hasSize;

public class ClientControllerTest {

	@InjectMocks
	private ClientController controller;
	
	private MockMvc mockMvc;
	
	private ObjectMapper objectMapper;
	private ClientDto client;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		objectMapper = new ObjectMapper();
		objectMapper.setSerializationInclusion(Include.NON_NULL);
		client = ClientDto.builder().id(1).name("test").phone("303-123-1234").build();
	}

	@Test
	public void testList() throws Exception {
		String jsonExpected = objectMapper.writeValueAsString(ImmutableList.of(client));

		mockMvc.perform(get("/v2/clients"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$", hasSize(1)))
			.andExpect(content().json(jsonExpected));
	}

	@Test
	public void testGet() throws Exception {
		String jsonExpected = objectMapper.writeValueAsString(client);
		
		mockMvc.perform(get("/v2/clients/1"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(content().json(jsonExpected));
	}

	@Test
	public void testSave() throws Exception {
		String content = objectMapper.writeValueAsString(client);

		mockMvc.perform(
				post("/v2/clients")
					.contentType(MediaType.APPLICATION_JSON_UTF8)
					.content(content))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(content().json(content));
	}

	@Test
	public void testUpdate() throws Exception {
		String content = objectMapper.writeValueAsString(client);

		mockMvc.perform(
				put("/v2/clients")
					.contentType(MediaType.APPLICATION_JSON_UTF8)
					.content(content))
			.andDo(print())
			.andExpect(status().isNoContent());
	}

}
