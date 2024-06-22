package com.exemple.simplifiedpicpay.app.mapper;

import com.exemple.simplifiedpicpay.core.domain.User;
import com.exemple.simplifiedpicpay.core.domain.dto.UserRequestDTO;
import com.exemple.simplifiedpicpay.core.domain.user.UserEntity;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

    User map(UserRequestDTO userRequestDTO);
    UserEntity map(User user);
    User map(UserEntity userEntity);
}
