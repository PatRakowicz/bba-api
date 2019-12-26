package com.bba.client.service;

import com.bba.client.ClientDto;
import com.bba.domain.ClientEntity;
import com.bba.client.repositories.ClientRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    private final ClientMapper mapper;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
        this.mapper = Mappers.getMapper(ClientMapper.class);
    }

    public List<ClientDto> getClients(Integer accountId) {
        // TODO page this, 63k bytes for a list is a bit much.
        List<ClientEntity> list = clientRepository.findAllByAccountId(accountId);
        List<ClientDto> dtoList = new ArrayList<>();
        for (ClientEntity clientEntity : list) {
            ClientDto dto = mapper.mapSlimDto(clientEntity);
            dtoList.add(dto);
        }
        return dtoList;
        //return list.stream().map(mapper::mapSlimDto).collect(Collectors.toList());
    }

    public ClientDto getClient(Integer clientId, Integer accountId) {
        ClientEntity entity = getClientEntity(clientId, accountId);
        return mapper.mapDto(entity);
    }

    public ClientDto saveClient(ClientDto dto, Integer accountId) {
        ClientEntity entity = mapper.mapEntity(dto);
        entity.setAccountId(accountId);
        entity = clientRepository.save(entity);
        return mapper.mapDto(entity);
    }

    @Transactional
    public ClientDto updateClient(ClientDto dto, Integer accountId) {
        ClientEntity entity = getClientEntity(dto.getId(), accountId);
        entity = mapper.mapExisting(dto, entity); // TODO address
        entity = clientRepository.save(entity);
        return mapper.mapDto(entity);
    }

    public void deleteClient(Integer clientId, Integer accountId) {
        ClientEntity entity = getClientEntity(clientId, accountId);
        entity.setStatus("I");
        clientRepository.save(entity);
    }

    private ClientEntity getClientEntity(Integer clientId, Integer accountId) {
        return clientRepository.findByIdAndAccountId(clientId, accountId).orElseThrow(
            () -> new RuntimeException("client NOT_FOUND cid=" + clientId));
    }
}
