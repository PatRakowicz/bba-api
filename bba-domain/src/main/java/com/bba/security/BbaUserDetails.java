package com.bba.security;

import com.bba.domain.BbaConstants;
import lombok.Builder;
import lombok.Getter;
import lombok.Singular;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.ZoneId;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
    @Singular
    private Map<String, String> settings;

    public ZoneId getZoneId() {
        try {
            return ZoneId.of(settings.get(BbaConstants.KEY_TIMEZONE));
        } catch (Exception ignore) {
            return ZoneId.of("GMT");
        }
    }

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BbaUserDetails that = (BbaUserDetails) o;
        return userId.equals(that.userId) &&
            accountId.equals(that.accountId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, accountId);
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

    public static BbaUserDetails getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof BbaUserDetails) {
            return (BbaUserDetails) principal;
        } else {
            throw new RuntimeException("User must be authenticated: " + principal);
        }
    }
}
