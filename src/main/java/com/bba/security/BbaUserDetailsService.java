package com.bba.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;

@Service
@Slf4j
public class BbaUserDetailsService implements UserDetailsService {

    private final boolean useCrypto;
    private final SecUserRepository secUserRepository;
    private final PasswordEncoder passwordEncoder;

    public BbaUserDetailsService(SecUserRepository secUserRepository, PasswordEncoder passwordEncoder, @Value("${spring.datasource.url}") String dsUrl) {
        this.secUserRepository = secUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.useCrypto = dsUrl.toLowerCase().contains(":mysql:");
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("authenticating {}", username);
        String remoteHost = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getRemoteHost();
        SecUserAccount secUserAccount = secUserRepository.queryByLoginName(username).orElseThrow(
            () -> new UsernameNotFoundException(username));
        String password = secUserAccount.getUser().getLoginPass();
        if (useCrypto) { // H2 fails as it does not have AES_DECRYPT function
            password = secUserRepository.decrypt(secUserAccount.getUser().getLoginPass());
        }

        secUserAccount.getUser().setLastIP(remoteHost);
        secUserAccount.getUser().setLastLogin(LocalDateTime.now());

        return BbaUserDetails.builder()
            .username(secUserAccount.getUser().getLoginName())
            .password(passwordEncoder.encode(password))
            .authority(new SimpleGrantedAuthority("ROLE_USER"))
            .accountId(secUserAccount.getAccount().getId())
            .businessName(secUserAccount.getAccount().getBusinessName())
            .userId(secUserAccount.getUser().getId())
            .build();
    }
}
