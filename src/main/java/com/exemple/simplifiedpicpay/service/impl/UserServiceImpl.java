package com.exemple.simplifiedpicpay.service.impl;

import com.exemple.simplifiedpicpay.domain.dto.UserDTO;
import com.exemple.simplifiedpicpay.domain.user.User;
import com.exemple.simplifiedpicpay.exception.BusinessErrorException;
import com.exemple.simplifiedpicpay.respositories.UserRepository;
import com.exemple.simplifiedpicpay.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import static com.exemple.simplifiedpicpay.utils.Utils.validateDocument;
import static com.exemple.simplifiedpicpay.utils.Utils.validateEmail;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(UserDTO request) throws BusinessErrorException {
        validateDocument(request.document(), request.userType());
        validateEmail(request.email());
        userRepository.findUserByDocument(request.document()).ifPresent(e -> {
            throw new DataIntegrityViolationException("User already castrated");
        });
        userRepository.findUserByEmail(request.email()).ifPresent(e -> {
            throw new DataIntegrityViolationException("User already castrated");
        });
        return userRepository.save(new User(request));
    }

    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
    }
}
