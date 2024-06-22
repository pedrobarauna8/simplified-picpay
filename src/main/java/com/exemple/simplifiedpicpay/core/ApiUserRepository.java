package com.exemple.simplifiedpicpay.core;

import com.exemple.simplifiedpicpay.core.domain.apiUser.ApiUserEntity;
import org.springframework.security.core.userdetails.UserDetails;

public interface ApiUserRepository {
    UserDetails findByLogin(String login);
    ApiUserEntity save(ApiUserEntity apiUserEntity);
}
