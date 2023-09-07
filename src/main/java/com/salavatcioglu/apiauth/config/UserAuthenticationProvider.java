package com.salavatcioglu.apiauth.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.nio.CharBuffer;
import java.util.Collections;

@Component
public class UserAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    PasswordEncoder passwordEncoder;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        logger.info("In UserAuthenticationProvider.");

        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        UserDetails userDetails = null;

        try {
            userDetails = userDetailsService.loadUserByUsername(username);
        } catch(UsernameNotFoundException e) {
            logger.debug("UsernameNotFound. ", e);
            return null;
        }

        if (passwordEncoder.matches(CharBuffer.wrap(password), userDetails.getPassword())) {
            return UsernamePasswordAuthenticationToken.authenticated(username, password, Collections.emptyList());
        }

        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.equals(authentication);
    }
}
