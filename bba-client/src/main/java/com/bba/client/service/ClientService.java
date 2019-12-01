package com.bba.client.service;

import com.bba.client.ClientDto;
import com.bba.repositories.ClientRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    private final ClientMapper mapper;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
        this.mapper = Mappers.getMapper(ClientMapper.class);
    }

    public List<ClientDto> getClients(Integer accountId) {
        List<ClientEntity> list = clientRepository.findAllByAccountId(accountId);
        return list.stream().map(mapper::mapDto).collect(Collectors.toList());
    }

    public ClientDto getClient(Integer accountId, Integer clientId) {
        ClientEntity entity = getClientEntity(accountId, clientId);
        return mapper.mapDto(entity);
    }

    public ClientDto saveClient(Integer accountId, ClientDto dto) {
        ClientEntity entity = mapper.mapEntity(dto);
        entity.setAccountId(accountId);
        entity = clientRepository.save(entity);
        return mapper.mapDto(entity);
    }

    @Transactional
    public ClientDto updateClient(Integer accountId, ClientDto dto) {
        ClientEntity entity = getClientEntity(accountId, dto.getId());
        entity = mapper.mapExisting(dto, entity); // TODO address
        entity = clientRepository.save(entity);
        return mapper.mapDto(entity);
    }

    public void deleteClient(Integer accountId, Integer clientId) {
        ClientEntity entity = getClientEntity(accountId, clientId);
        entity.setStatus("D"); // soft delete
        clientRepository.save(entity);
    }

    private ClientEntity getClientEntity(Integer accountId, Integer clientId) {
        return clientRepository.findByIdAndAccountId(clientId, accountId).orElseThrow(
            () -> new RuntimeException("client NOT_FOUND cid=" + clientId));
    }
}
