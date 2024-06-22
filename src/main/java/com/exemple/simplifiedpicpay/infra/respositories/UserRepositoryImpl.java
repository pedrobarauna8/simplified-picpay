package com.exemple.simplifiedpicpay.infra.respositories;

import com.exemple.simplifiedpicpay.core.UserRepository;
import com.exemple.simplifiedpicpay.core.domain.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepositoryImpl extends JpaRepository<UserEntity, Long>, UserRepository {
    Optional<UserEntity> findUserByDocument(String document);

    Optional<UserEntity> findUserByEmail(String email);
}
