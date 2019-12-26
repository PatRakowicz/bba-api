package com.bba.security;

import com.bba.domain.AccountEntity;
import com.bba.domain.UserEntity;

public class SecUserAccount {

    private UserEntity user;
    private AccountEntity account;

    public SecUserAccount(UserEntity user, AccountEntity account) {
        this.user = user;
        this.account = account;
    }

    public UserEntity getUser() {
        return user;
    }

    public AccountEntity getAccount() {
        return account;
    }
}
