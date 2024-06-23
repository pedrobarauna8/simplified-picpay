package com.exemple.simplifiedpicpay.app.mapper;

import com.exemple.simplifiedpicpay.core.domain.User;
import com.exemple.simplifiedpicpay.core.domain.dto.UserRequestDTO;
import com.exemple.simplifiedpicpay.core.domain.user.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserMapper {

    @Mapping(source = "document", target = "document.number")
    User map(UserRequestDTO userRequestDTO);

    UserEntity map(User user);

    @Mapping(source = "document", target = "document.number")
    User map(UserEntity userEntity);
}
