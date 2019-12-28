package com.bba.security;

import com.bba.domain.SettingsEntity;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface SecSettingsRepository extends Repository<SettingsEntity, Integer> {

    List<SettingsEntity> findAllByAccountId(Integer accountId);
}
