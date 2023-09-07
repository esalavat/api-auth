package com.salavatcioglu.apiauth.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


@Component
public class UserAuthenticationConverter implements AuthenticationConverter {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Authentication convert(HttpServletRequest request) {
        UserDto userDto = null;
        try {
            userDto = objectMapper.readValue(request.getInputStream(), UserDto.class);
        } catch (IOException e) {
            return null;
        }
        return UsernamePasswordAuthenticationToken.unauthenticated(userDto.getLogin(), userDto.getPassword());
    }
}
