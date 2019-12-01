package com.bba.client.service;

import com.bba.client.ClientDto;
import com.bba.repositories.ClientRepository;
import com.google.common.collect.ImmutableList;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class ClientServiceTest {

    private static final Integer ACC_ID = 1;
    private static final Integer CLIENT_ID = 2;

    @InjectMocks
    private ClientService service;

    @Mock
    private ClientRepository clientRepository;

    @Captor
    private ArgumentCaptor<ClientEntity> captor;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void getClients() {
        ClientEntity entity = ClientEntity.builder().id(CLIENT_ID).name("name").phone("303").build();
        List<ClientEntity> list = ImmutableList.of(entity);
        when(clientRepository.findAllByAccountId(ACC_ID)).thenReturn(list);

        List<ClientDto> result = service.getClients(ACC_ID);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertNotNull(result.get(0));
        assertEquals(entity.getId(), result.get(0).getId());
        assertEquals(entity.getName(), result.get(0).getName());
        assertEquals(entity.getPhone(), result.get(0).getPhone());
        verify(clientRepository).findAllByAccountId(any());
        verifyNoMoreInteractions(clientRepository);
    }

    @Test
    public void getClient() {
        ClientEntity entity = ClientEntity.builder().id(CLIENT_ID).name("name").phone("303").build();
        when(clientRepository.findByIdAndAccountId(CLIENT_ID, ACC_ID)).thenReturn(Optional.of(entity));

        ClientDto result = service.getClient(ACC_ID, CLIENT_ID);

        assertNotNull(result);
        assertEquals(entity.getId(), result.getId());
        assertEquals(entity.getName(), result.getName());
        assertEquals(entity.getPhone(), result.getPhone());
        verify(clientRepository).findByIdAndAccountId(any(), any());
        verifyNoMoreInteractions(clientRepository);
    }

    @Test
    public void saveClient() {
        ClientDto dto = ClientDto.builder().name("name").phone("303").build();
        when(clientRepository.save(any())).thenAnswer(invocation -> invocation.getArguments()[0]);

        ClientDto result = service.saveClient(ACC_ID, dto);

        assertNotNull(result);
        assertEquals(dto.getName(), result.getName());
        assertEquals(dto.getPhone(), result.getPhone());
        verify(clientRepository).save(captor.capture());
        ClientEntity saved = captor.getValue();
        assertNotNull(saved);
        assertEquals(dto.getName(), saved.getName());
        assertEquals(dto.getPhone(), saved.getPhone());
        verifyNoMoreInteractions(clientRepository);
    }

    @Test
    public void updateClient() {
        ClientEntity entity = ClientEntity.builder().id(CLIENT_ID).name("name").phone("303").build();
        when(clientRepository.findByIdAndAccountId(CLIENT_ID, ACC_ID)).thenReturn(Optional.of(entity));
        when(clientRepository.save(any())).thenAnswer(invocation -> invocation.getArguments()[0]);
        ClientDto dto = ClientDto.builder().id(CLIENT_ID).name("name1").phone("304").build();

        ClientDto result = service.updateClient(ACC_ID, dto);

        assertNotNull(result);
        assertEquals(dto.getName(), result.getName());
        assertEquals(dto.getPhone(), result.getPhone());
        verify(clientRepository).save(captor.capture());
        ClientEntity saved = captor.getValue();
        assertNotNull(saved);
        assertEquals(dto.getName(), saved.getName());
        assertEquals(dto.getPhone(), saved.getPhone());
        verify(clientRepository).findByIdAndAccountId(CLIENT_ID, ACC_ID);
        verifyNoMoreInteractions(clientRepository);
    }

    @Test
    public void deleteClient() {
        ClientEntity entity = ClientEntity.builder().id(CLIENT_ID).name("name").phone("303").build();
        when(clientRepository.findByIdAndAccountId(CLIENT_ID, ACC_ID)).thenReturn(Optional.of(entity));

        service.deleteClient(ACC_ID, CLIENT_ID);

        verify(clientRepository).save(captor.capture());
        ClientEntity saved = captor.getValue();
        assertNotNull(saved);
        assertEquals("D", saved.getStatus());
        verify(clientRepository).findByIdAndAccountId(CLIENT_ID, ACC_ID);
        verifyNoMoreInteractions(clientRepository);
    }
}