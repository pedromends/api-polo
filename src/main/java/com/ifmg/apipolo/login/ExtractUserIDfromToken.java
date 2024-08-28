package com.ifmg.apipolo.login;

import com.ifmg.apipolo.service.CustomUserDetailsService;
import com.ifmg.apipolo.service.JwtTokenService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;

public class ExtractUserIDfromToken {

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private MyCustomUserDetails myCustomUserDetails;

    public Long getUserIDfromToken(HttpServletRequest request) {
        Long userId = null;

        String authHeader = request.getHeader("Authorization");
        String jwtToken;
        String userEmail = null;

        if(authHeader != null || authHeader.startsWith("Bearer ")){

            jwtToken = authHeader.substring(7);
            userEmail = jwtTokenService.extractUsername(jwtToken);
        }

        if(userEmail != null){
            myCustomUserDetails = customUserDetailsService.loadUserByUsername(userEmail);
            userId = myCustomUserDetails.getUserId();
        }

        return userId;
    }
}
