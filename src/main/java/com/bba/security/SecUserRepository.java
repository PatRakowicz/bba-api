package com.bba.security;

import com.bba.domain.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.nio.charset.Charset;
import java.util.Optional;

public interface SecUserRepository extends CrudRepository<UserEntity, Integer> {

    String KPS = "b500d25f4ff4d4e453af77b8cf762dff";

    Optional<UserEntity> findByLoginName(String loginName);

    @Query("select new com.bba.security.SecUserAccount(u, a) from " +
        "UserEntity u, AccountEntity a, UserAccountEntity ua " +
        "where u.loginName = :loginName " +
        "and ua.userId = u.id " +
        "and a.id = ua.accountId ")
    Optional<SecUserAccount> queryByLoginName(String loginName);

    @Query(nativeQuery = true,
        value = "select hex(aes_encrypt(:value,:key)) as encryted from dual")
    String encrypt(String value, String key);

    default String encrypt(String value) {
        return encrypt(value, KPS);
    }

    @Query(nativeQuery = true,
        value = "select aes_decrypt(unhex(:value),:key) as decryted from dual")
    byte[] decrypt(String value, String key);

    default String decrypt(String value) {
        return new String(decrypt(value, KPS), Charset.defaultCharset());
    }
}
