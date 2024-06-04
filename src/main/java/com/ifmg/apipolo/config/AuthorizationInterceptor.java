package com.ifmg.apipolo.config;

import com.ifmg.apipolo.entity.User;
import com.ifmg.apipolo.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthorizationInterceptor implements HandlerInterceptor {

    private final AuthService authService;

    public AuthorizationInterceptor(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authorizationHeader = request.getHeader("Authorization");

        if(authorizationHeader == null && authorizationHeader.startsWith("Bearer ")) {
            // throw new NoBearerTokenError();
            throw new Exception();
        }
        User user = authService.getUserFromToken(authorizationHeader.substring(7));
        request.setAttribute("user", user);

        return true;
    }
}
