package com.exemple.simplifiedpicpay.controller;

import com.exemple.simplifiedpicpay.app.controller.ApiAuthenticationController;
import com.exemple.simplifiedpicpay.core.domain.apiUser.ApiUserEntity;
import com.exemple.simplifiedpicpay.core.domain.apiUser.ApiUserRole;
import com.exemple.simplifiedpicpay.app.dto.ApiLoginRequestDTO;
import com.exemple.simplifiedpicpay.app.dto.ApiLoginResponseDTO;
import com.exemple.simplifiedpicpay.core.domain.dto.ApiRegisterRequestDTO;
import com.exemple.simplifiedpicpay.infra.respositories.ApiUserRepositoryImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class ApiAuthenticationControllerTest {

    @InjectMocks
    private ApiAuthenticationController controller;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private TokenService service;

    @Mock
    private ApiUserRepositoryImpl repository;

    @Mock
    private UserDetails userDetails;

    @Test
    @DisplayName("Test login api with success")
    void loginTestCase1() {
        var request = new ApiLoginRequestDTO("user","password");
        var usernamePassword = new UsernamePasswordAuthenticationToken(new ApiUserEntity("", request.login(), request.password(), ApiUserRole.ADMIN), null);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(usernamePassword);
        when(service.getToken(any(ApiUserEntity.class))).thenReturn("");
        var response = controller.login(request);
        assertEquals(response, ResponseEntity.ok(new ApiLoginResponseDTO("")));
    }

    @Test
    @DisplayName("Test register api with success")
    void registerTestCase1() {
        var request = new ApiRegisterRequestDTO("","",ApiUserRole.ADMIN);
        var response = controller.register(request);
        assertEquals(response, ResponseEntity.ok().build());
    }

    @Test
    @DisplayName("Test register api with when the api user already registered")
    void registerTestCase2() {
        var request = new ApiRegisterRequestDTO("","",ApiUserRole.ADMIN);
        when(repository.findByLogin(request.login())).thenReturn(userDetails);
        var response = controller.register(request);
        assertEquals(response, ResponseEntity.badRequest().build());
    }
}