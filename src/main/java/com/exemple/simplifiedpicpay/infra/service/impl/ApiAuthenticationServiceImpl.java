package com.exemple.simplifiedpicpay.infra.service.impl;

import com.exemple.simplifiedpicpay.infra.respositories.ApiUserRepositoryImpl;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
@Service
public class ApiAuthenticationServiceImpl implements UserDetailsService {

    ApiUserRepositoryImpl userRepository;

    public ApiAuthenticationServiceImpl(ApiUserRepositoryImpl userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByLogin(username);
    }
}
