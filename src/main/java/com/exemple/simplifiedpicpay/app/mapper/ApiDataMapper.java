package com.exemple.simplifiedpicpay.app.mapper;

import com.exemple.simplifiedpicpay.core.domain.ApiLoginData;
import com.exemple.simplifiedpicpay.core.domain.ApiRegisterData;
import com.exemple.simplifiedpicpay.app.dto.ApiLoginRequestDTO;
import com.exemple.simplifiedpicpay.core.domain.dto.ApiRegisterRequestDTO;
import org.mapstruct.Mapper;

@Mapper
public interface ApiDataMapper {
    ApiLoginData map(ApiLoginRequestDTO apiLoginRequestDTO);
    ApiRegisterData map(ApiRegisterRequestDTO apiRegisterRequestDTO);
}
