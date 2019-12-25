package com.bba.security;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

@Getter
@Builder
public class BbaUserDetails implements UserDetails {

    private String username;
    private String password;
    @Singular
    private List<GrantedAuthority> authorities;

    private Integer userId;
    private Integer accountId;
    private String businessName;

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "BbaUserDetails{" +
            "username='" + username + '\'' +
            ", password='[protected]'" +
            ", authorities=" + authorities +
            ", userId=" + userId +
            ", accountId=" + accountId +
            ", businessName='" + businessName + '\'' +
            '}';
    }
}
