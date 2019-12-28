package com.bba.security;

import com.bba.domain.AccountEntity;
import com.bba.domain.SettingsEntity;
import com.bba.domain.UserEntity;
import com.google.common.collect.ImmutableList;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class BbaUserDetailsServiceTest {

    private BbaUserDetailsService service;

    @Mock
    private SecUserRepository secUserRepository;
    @Mock
    private SecSettingsRepository secSettingsRepository;
    @Mock
    private PasswordEncoder passwordEncoder;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        service = new BbaUserDetailsService(secUserRepository, secSettingsRepository, passwordEncoder, "url");
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRemoteHost("host");
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    }

    @Test
    public void loadUserByUsername() {
        String login = "test";
        SecUserAccount userAccount = new SecUserAccount(
            UserEntity.builder().id(1).loginName(login).loginPass("pass").build(),
            AccountEntity.builder().id(11).businessName("business").build()
        );
        when(secUserRepository.queryByLoginName(login)).thenReturn(Optional.of(userAccount));
        when(secSettingsRepository.findAllByAccountId(11))
            .thenReturn(ImmutableList.of(SettingsEntity.builder().key("key").val("val").build()));
        when(passwordEncoder.encode(any())).thenAnswer(invocation -> invocation.getArgument(0));

        BbaUserDetails result = (BbaUserDetails) service.loadUserByUsername(login);

        // returned values
        assertNotNull(result);
        assertEquals(userAccount.getUser().getId(), result.getUserId());
        assertEquals(userAccount.getUser().getLoginName(), result.getUsername());
        assertEquals(userAccount.getAccount().getId(), result.getAccountId());
        assertEquals(userAccount.getAccount().getBusinessName(), result.getBusinessName());
        assertNotNull(result.getSettings());
        assertEquals(1, result.getSettings().size());

        // saved values
        assertNotNull(userAccount.getUser().getLastIP());
        assertNotNull(userAccount.getUser().getLastLogin());

        verify(secUserRepository).queryByLoginName(login);
        verify(secSettingsRepository).findAllByAccountId(any());
        verify(passwordEncoder).encode(any());
        verifyNoMoreInteractions(secUserRepository, passwordEncoder);
    }

    @Test
    public void loadUserByUsername_notFound() {
        String login = "test";

        try {
            service.loadUserByUsername(login);
            fail("Should have failed with user not found");
        } catch (UsernameNotFoundException expected) {
        }

        verify(secUserRepository).queryByLoginName(login);
        verifyNoMoreInteractions(secUserRepository, passwordEncoder);
    }
}
