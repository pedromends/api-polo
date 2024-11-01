package com.ifmg.apipolo.login;

import com.ifmg.apipolo.service.CustomUserDetailsService;
import com.ifmg.apipolo.service.JwtTokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private CustomUserDetailsService myCustomUserDetailService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String authHeader = request.getHeader("Authorization");
            String jwtToken = null;
            String userEmail = null;

            if(authHeader == null || !authHeader.startsWith("Bearer ")){
                filterChain.doFilter(request, response);
                return;
            }

            jwtToken = authHeader.substring(7);
            userEmail = jwtTokenService.extractUsername(jwtToken);

            if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null){
                MyCustomUserDetails myCustomUserDetails =  myCustomUserDetailService.loadUserByUsername(userEmail);

                if(jwtTokenService.validateToken(jwtToken, myCustomUserDetails)){
//                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(myCustomUserDetails, null, myCustomUserDetails.getAuthorities());
                    //SecurityContextHolder.getContext().setAuthentication(auth);
                    SecurityContext securityContext = SecurityContextHolder.createEmptyContext();

                    UsernamePasswordAuthenticationToken userAuthToken =
                            new UsernamePasswordAuthenticationToken(
                                    myCustomUserDetails,
                                    SecurityContextHolder.getContext().getAuthentication(),
                                    myCustomUserDetails.getAuthorities()
                            );
                    userAuthToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    securityContext.setAuthentication(userAuthToken);
                    SecurityContextHolder.getContext().setAuthentication(userAuthToken);

                    logger.info("User authenticated: " + userAuthToken + ", setting security context");
                }
            }
            filterChain.doFilter(request, response);
        } catch(IOException e){
            System.out.println(e);
        }
    }
}
