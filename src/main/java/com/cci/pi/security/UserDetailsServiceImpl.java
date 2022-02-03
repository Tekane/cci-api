package com.cci.pi.security;
import com.cci.pi.config.RestSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Value("${cci.api.username}")
    private String username;
    @Value("${cci.api.password}")
    private String password;

    @Autowired
    private RestSecurityConfig restSecurityConfig;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new User(username, restSecurityConfig.bCryptPasswordEncoder().encode(password), new ArrayList<>());
    }
}
