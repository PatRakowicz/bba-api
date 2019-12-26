package com.bba;

import com.bba.security.BbaUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

import javax.servlet.http.HttpServletResponse;

@Configuration
@Slf4j
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/h2/**");
    }

    @Override
    protected UserDetailsService userDetailsService() {
        return getApplicationContext().getBean(BbaUserDetailsService.class);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http
//            .sessionManagement()
//            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//        .and()
            .csrf().disable() // .csrfTokenRepository(new CookieCsrfTokenRepository())
        //.and()
            .formLogin()
                .successHandler((request, response, authentication) -> {
                    log.debug("auth success: {}", authentication.getPrincipal());
                    response.setContentType("application/json;charset=UTF-8");
                    response.getWriter().print("{ \"authenticated\": true }");
                })
                .failureHandler((request, response, exception) -> {
                    log.debug("auth error: {}", exception.getMessage());
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "UNAUTHORIZED");
                })
                .permitAll()
        .and()
            .logout()
                .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler())
                // .deleteCookies("remember-me")
                .permitAll()
//        .and()
//            .rememberMe().rememberMeServices(TODO).cookieName("X-auth-token").alwaysRemember(true)
        .and()
            .authorizeRequests().anyRequest().authenticated()
        .and()
            .exceptionHandling()
                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.FORBIDDEN))
        ;

        // @formatter:on
    }
}
