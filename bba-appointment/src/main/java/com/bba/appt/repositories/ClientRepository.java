package com.bba.appt.repositories;

import com.bba.domain.ClientEntity;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface ClientRepository extends Repository<ClientEntity, Integer> {

    Optional<ClientEntity> findByIdAndAccountId(Integer clientId, Integer accountId);
}
