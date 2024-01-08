package com.exemple.simplifiedpicpay.respositories;

import com.exemple.simplifiedpicpay.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByDocument(String document);
    Optional<User> findUserByEmail(String email);
}
