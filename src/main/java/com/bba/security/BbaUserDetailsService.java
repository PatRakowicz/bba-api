package com.bba.security;

import com.bba.domain.SettingsEntity;
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
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BbaUserDetailsService implements UserDetailsService {

    private final boolean useCrypto;
    private final SecUserRepository secUserRepository;
    private final SecSettingsRepository secSettingsRepository;
    private final PasswordEncoder passwordEncoder;

    public BbaUserDetailsService(
        SecUserRepository secUserRepository,
        SecSettingsRepository secSettingsRepository,
        PasswordEncoder passwordEncoder,
        @Value("${spring.datasource.url}") String dsUrl
    ) {
        this.secUserRepository = secUserRepository;
        this.secSettingsRepository = secSettingsRepository;
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
        Map<String, String> settings = secSettingsRepository.findAllByAccountId(secUserAccount.getAccount().getId())
            .stream().collect(Collectors.toMap(SettingsEntity::getKey, SettingsEntity::getVal));

        // jpa/hibernate will flash/save these automatically
        secUserAccount.getUser().setLastIP(remoteHost);
        secUserAccount.getUser().setLastLogin(LocalDateTime.now());

        return BbaUserDetails.builder()
            .userId(secUserAccount.getUser().getId())
            .accountId(secUserAccount.getAccount().getId())
            .username(secUserAccount.getUser().getLoginName())
            .password(passwordEncoder.encode(password))
            .authority(new SimpleGrantedAuthority("ROLE_USER"))
            .businessName(secUserAccount.getAccount().getBusinessName())
            .settings(settings)
            .build();
    }
}
