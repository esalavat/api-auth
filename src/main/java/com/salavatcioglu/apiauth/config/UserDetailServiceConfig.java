package com.salavatcioglu.apiauth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class UserDetailServiceConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("user")
                        .password("$2a$12$CxiV/jrvr4hpNN75ZjCt7O9J4NVXp0QpDLhONf1gofS9HDqmJMd1m")
                        .roles("USER")
                        .build();

        return new InMemoryUserDetailsManager(user);
    }

}
