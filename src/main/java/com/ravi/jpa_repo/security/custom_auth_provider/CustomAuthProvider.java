package com.ravi.jpa_repo.security.custom_auth_provider;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomAuthProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName= authentication.getName();
        String password= authentication.getCredentials().toString();

        if(userName.equalsIgnoreCase("ravi") && password.equalsIgnoreCase("pass")) {
            return new UsernamePasswordAuthenticationToken(userName, password, List.of());
        } else {
            //invalid user
            throw new BadCredentialsException("Invalid Username or Password");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
