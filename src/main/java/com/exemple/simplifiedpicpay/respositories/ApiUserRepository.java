package com.exemple.simplifiedpicpay.respositories;

import com.exemple.simplifiedpicpay.domain.apiUser.ApiUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface ApiUserRepository extends JpaRepository<ApiUser, String> {
    UserDetails findByLogin(String login);
}
