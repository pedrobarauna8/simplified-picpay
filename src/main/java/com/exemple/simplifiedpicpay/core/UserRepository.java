package com.exemple.simplifiedpicpay.core;

import com.exemple.simplifiedpicpay.core.domain.user.UserEntity;

import java.util.Optional;

public interface UserRepository {

    Optional<UserEntity> findUserByDocument(String document);

    Optional<UserEntity> findUserByEmail(String email);

    Optional<UserEntity> findById(Long id);

    UserEntity save(UserEntity user);
}
