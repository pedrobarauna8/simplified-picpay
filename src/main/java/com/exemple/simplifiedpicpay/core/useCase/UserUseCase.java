package com.exemple.simplifiedpicpay.core.useCase;

import com.exemple.simplifiedpicpay.app.mapper.UserMapper;
import com.exemple.simplifiedpicpay.core.UserRepository;
import com.exemple.simplifiedpicpay.core.domain.User;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

@Component
public class UserUseCase {

    private final UserRepository userRepository;
    private final UserMapper mapper;

    public UserUseCase(UserRepository userRepository,
                       UserMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    public User createUser(User user) {

        userRepository.findUserByDocument(user.getDocument()).ifPresent(e -> {
            throw new DataIntegrityViolationException("User already castrated");
        });
        userRepository.findUserByEmail(user.getEmail()).ifPresent(e -> {
            throw new DataIntegrityViolationException("User already castrated");
        });

        var userEntity = mapper.map(user);

        return mapper.map(userRepository.save(userEntity));
    }

    public User getUser(Long id) {
        return mapper.map(userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found")));
    }
}
