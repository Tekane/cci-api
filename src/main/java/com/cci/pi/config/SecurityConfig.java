package com.cci.pi.config;

import com.cci.pi.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@Configuration
public class SecurityConfig  extends WebSecurityConfigurerAdapter {

    final private UserDetailsServiceImpl userDetailsService;
    final private RestSecurityConfig restSecurityConfig;

    public SecurityConfig(UserDetailsServiceImpl userDetailsService, RestSecurityConfig restSecurityConfig) {
        this.userDetailsService = userDetailsService;
        this.restSecurityConfig = restSecurityConfig;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic()
                .authenticationEntryPoint(restSecurityConfig)
                .and()
                .csrf()
                .disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .anyRequest().authenticated();
    }

    @Autowired
    @Transactional
    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(restSecurityConfig.bCryptPasswordEncoder());
    }
}
