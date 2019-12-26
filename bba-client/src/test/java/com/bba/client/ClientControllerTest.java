package com.bba.client;

import com.bba.client.service.ClientService;
import com.bba.security.BbaUserDetails;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ClientControllerTest {

    private static final Integer ACCT_ID = 1;
    private static final Integer CLIENT_ID = 10;

    @InjectMocks
    private ClientController controller;

    @Mock
    private ClientService clientService;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;
    private ClientDto client;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(Include.NON_NULL);
        client = ClientDto.builder().id(CLIENT_ID).name("test").phone("303-123-1234").build();

        SecurityContext securityContext = mock(SecurityContext.class);
        Authentication authentication = mock(Authentication.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(BbaUserDetails.builder().accountId(ACCT_ID).build());
        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    public void testList() throws Exception {
        when(clientService.getClients(ACCT_ID)).thenReturn(ImmutableList.of(client));
        String jsonExpected = objectMapper.writeValueAsString(ImmutableList.of(client));

        mockMvc.perform(get("/v2/clients"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(jsonPath("$", hasSize(1)))
            .andExpect(content().json(jsonExpected));

        verify(clientService).getClients(any());
        verifyNoMoreInteractions(clientService);
    }

    @Test
    public void testGet() throws Exception {
        when(clientService.getClient(CLIENT_ID, ACCT_ID)).thenReturn(client);
        String jsonExpected = objectMapper.writeValueAsString(client);

        mockMvc.perform(get("/v2/clients/" + CLIENT_ID))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(content().json(jsonExpected));

        verify(clientService).getClient(anyInt(), any());
        verifyNoMoreInteractions(clientService);
    }

    @Test
    public void testSave() throws Exception {
        when(clientService.saveClient(any(ClientDto.class), eq(ACCT_ID))).thenAnswer(invocation -> invocation.getArguments()[0]);
        String content = objectMapper.writeValueAsString(client);

        mockMvc.perform(
            post("/v2/clients")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(content().json(content));

        verify(clientService).saveClient(any(ClientDto.class), anyInt());
        verifyNoMoreInteractions(clientService);
    }

    @Test
    public void testUpdate() throws Exception {
        when(clientService.updateClient(any(ClientDto.class), eq(ACCT_ID))).thenAnswer(invocation -> invocation.getArguments()[0]);
        String content = objectMapper.writeValueAsString(client);

        mockMvc.perform(
            put("/v2/clients")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content))
            .andDo(print())
            .andExpect(status().isNoContent());

        verify(clientService).updateClient(any(ClientDto.class), anyInt());
        verifyNoMoreInteractions(clientService);
    }
}
