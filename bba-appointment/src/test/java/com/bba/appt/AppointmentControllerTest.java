package com.bba.appt;

import com.bba.appt.service.AppointmentService;
import com.bba.domain.AppointmentEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class AppointmentControllerTest {

    @InjectMocks
    private AppointmentController controller;

    @Mock
    private AppointmentService service;
    @Captor
    private ArgumentCaptor<AppointmentDto> captor;

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
        when(service.getList(any(), any(), any())).thenReturn(ImmutableList.of(appointment));
        String jsonExpected = objectMapper.writeValueAsString(
            AppointmentListDto.builder().result(ImmutableList.of(appointment)).build());

        mockMvc.perform(get("/v2/appts"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(content().json(jsonExpected));

        verify(service).getList(any(), any(), any());
        verifyNoMoreInteractions(service);
    }

    @Test
    public void testGet() throws Exception {
        when(service.getAppointment(appointment.getId(), 100)).thenReturn(appointment);
        String jsonExpected = objectMapper.writeValueAsString(appointment);

        mockMvc.perform(get("/v2/appts/10"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(content().json(jsonExpected));

        verify(service).getAppointment(appointment.getId(), 100);
        verifyNoMoreInteractions(service);
    }

    @Test
    public void testSave() throws Exception {
        when(service.save(any(), any())).thenAnswer(invocation -> invocation.getArgument(0));
        String content = objectMapper.writeValueAsString(appointment);

        mockMvc.perform(
            post("/v2/appts")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(content().json(content));

        verify(service).save(any(), any());
        verifyNoMoreInteractions(service);
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

        verify(service).update(any(), any());
        verifyNoMoreInteractions(service);
    }

    @Test
    public void testDelete() throws Exception {
        String content = objectMapper.writeValueAsString(appointment);

        mockMvc.perform(
            delete("/v2/appts")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content))
            .andDo(print())
            .andExpect(status().isNoContent());

        verify(service).delete(any(), any());
        verifyNoMoreInteractions(service);
    }
}
