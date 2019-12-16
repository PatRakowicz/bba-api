package com.bba.appt;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class AppointmentControllerTest {

    @InjectMocks
    private AppointmentController controller;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private AppointmentDto appointment;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        appointment = AppointmentDto.builder().id(10).name("name").build();
    }

    @Test
    public void testList() throws Exception {
        String jsonExpected = objectMapper.writeValueAsString(ImmutableList.of(appointment));

        mockMvc.perform(get("/v2/appts"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(jsonPath("$", hasSize(1)))
            .andExpect(content().json(jsonExpected));
    }

    @Test
    public void testGet() throws Exception {
        String jsonExpected = objectMapper.writeValueAsString(appointment);

        mockMvc.perform(get("/v2/appts/10"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(content().json(jsonExpected));
    }

    @Test
    public void testSave() throws Exception {
        String content = objectMapper.writeValueAsString(appointment);

        mockMvc.perform(
            post("/v2/appts")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(content().json(content));
    }

    @Test
    public void testUpdate() throws Exception {
        String content = objectMapper.writeValueAsString(appointment);

        mockMvc.perform(
            put("/v2/appts")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content))
            .andDo(print())
            .andExpect(status().isNoContent());
    }
}