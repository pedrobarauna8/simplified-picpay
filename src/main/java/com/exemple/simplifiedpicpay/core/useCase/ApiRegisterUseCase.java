package com.exemple.simplifiedpicpay.core.useCase;

import com.exemple.simplifiedpicpay.core.domain.apiUser.ApiUserEntity;
import com.exemple.simplifiedpicpay.core.exception.BusinessErrorException;
import com.exemple.simplifiedpicpay.core.ApiUserRepository;
import com.exemple.simplifiedpicpay.core.domain.ApiRegisterData;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class ApiRegisterUseCase {

    private final ApiUserRepository apiUserRepository;

    public ApiRegisterUseCase(ApiUserRepository apiUserRepository) {
        this.apiUserRepository = apiUserRepository;
    }

    public void register(ApiRegisterData apiRegisterData) {

        if (apiUserRepository.findByLogin(apiRegisterData.login()) != null) throw new BusinessErrorException("User already exists");

        var encryptedPassword = new BCryptPasswordEncoder().encode(apiRegisterData.password());
        var apiUser = new ApiUserEntity(apiRegisterData.login(), encryptedPassword, apiRegisterData.role());

        apiUserRepository.save(apiUser);
    }
}
