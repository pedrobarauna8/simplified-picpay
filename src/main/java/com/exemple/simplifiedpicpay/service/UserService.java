package com.exemple.simplifiedpicpay.service;

import com.exemple.simplifiedpicpay.domain.dto.UserDTO;
import com.exemple.simplifiedpicpay.domain.user.User;

public interface UserService {
    public User createUser(UserDTO request);

    public User getUser(Long id);
}
