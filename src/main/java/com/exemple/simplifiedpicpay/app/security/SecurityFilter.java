package com.exemple.simplifiedpicpay.app.security;

import com.exemple.simplifiedpicpay.core.provider.ApiLoginProvider;
import com.exemple.simplifiedpicpay.infra.respositories.ApiUserRepositoryImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
@Component
public class SecurityFilter extends OncePerRequestFilter {

    ApiLoginProvider apiLoginProvider;

    ApiUserRepositoryImpl apiUserRepository;

    public SecurityFilter(@Lazy ApiLoginProvider apiLoginProvider,
                          ApiUserRepositoryImpl apiUserRepository) {
        this.apiLoginProvider = apiLoginProvider;
        this.apiUserRepository = apiUserRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = recoverToken(request);
        if (token != null) {

            var login = apiLoginProvider.validateToken(token);
            var user = apiUserRepository.findByLogin(login);

            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }
}
