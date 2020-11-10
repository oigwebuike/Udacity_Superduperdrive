package com.onyeka.project1.services;



import com.onyeka.project1.mapper.UserMapper;
import com.onyeka.project1.model.User;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.message.AuthException;
import javax.security.auth.message.config.AuthConfigProvider;
import javax.security.auth.message.config.ClientAuthConfig;
import javax.security.auth.message.config.ServerAuthConfig;
import java.util.ArrayList;

@Service
public class AuthenticationService implements AuthenticationProvider {


    private HashService hashService;
    private UserMapper userMapper;

    public AuthenticationService(HashService hashService, UserMapper userMapper) {
        this.hashService = hashService;
        this.userMapper = userMapper;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        User user = userMapper.getUser(username);
        if (user != null){
            String encodedSalt = user.getSalt();
            String hashedPassword = hashService.getHashedValue(password, encodedSalt);
            if (user.getPassword().equals(hashedPassword)){
                return new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>());
            }
        }

    return null;
    }

    @Override
    public boolean supports(Class<?> authentication){

        return authentication.equals(UsernamePasswordAuthenticationToken.class);
       // (or)
        // return UsernamePasswordAuthenticationToken.class.equals(authentication);

    }





}


