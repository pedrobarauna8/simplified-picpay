package com.exemple.simplifiedpicpay.infra.respositories;

import com.exemple.simplifiedpicpay.core.ApiUserRepository;
import com.exemple.simplifiedpicpay.core.domain.apiUser.ApiUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface ApiUserRepositoryImpl extends JpaRepository<ApiUserEntity, String>, ApiUserRepository {
    UserDetails findByLogin(String login);
}
