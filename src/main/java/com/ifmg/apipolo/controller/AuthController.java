package com.ifmg.apipolo.controller;

import com.ifmg.apipolo.entity.ForgotRequest;
import com.ifmg.apipolo.entity.ForgotResponse;
import com.ifmg.apipolo.entity.Login;
import com.ifmg.apipolo.entity.User;
import com.ifmg.apipolo.login.MyCustomUserDetails;
import com.ifmg.apipolo.service.AuthService;
import com.ifmg.apipolo.service.CustomUserDetailsService;
import com.ifmg.apipolo.service.JwtTokenService;
import com.ifmg.apipolo.service.LogoutResponse;
import com.ifmg.apipolo.vo.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@CrossOrigin("*")
@RestController
@RequestMapping("/user")
public class AuthController {

    @Autowired
    AuthService authService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtTokenService jwtTokenService;

    @GetMapping("/hello")
    public ResponseEntity<String> helloWorld()  {
        return new ResponseEntity<>("Hello World", HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<String> createUser(@RequestBody UserVO userVO, HttpServletResponse response)  {
        String currToken = authService.registerUser(userVO);

        Cookie cookie = new Cookie("refresh_token", currToken);
        response.addCookie(cookie);
        return new ResponseEntity<>(currToken, HttpStatus.OK);
    }

    @PostMapping("/refresh")
    public ResponseEntity<RefreshResponse> refresh(@CookieValue("refresh_token") String refreshToken, HttpServletResponse response){
        String currToken = authService.refreshAccess(refreshToken).getCurrentAccess();

        Cookie cookie = new Cookie("refresh_token", currToken);
        response.addCookie(cookie);
        return new ResponseEntity<>(new RefreshResponse(currToken), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest)  {

        try{
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
            Authentication authentication = authenticationManager.authenticate(authToken);

            MyCustomUserDetails userDetailsService = customUserDetailsService.loadUserByUsername(loginRequest.getEmail());
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = jwtTokenService.generateToken(userDetailsService);
            return new ResponseEntity<>(new LoginResponse(token, userDetailsService), HttpStatus.OK);
        }catch(AuthenticationException e){
            return new ResponseEntity<>(new LoginResponse(e.getMessage()), HttpStatus.UNAUTHORIZED);
        }



//        OLD METHOD
//        HttpServletResponse response
//        Login login = authService.login(loginRequest.getEmail(), loginRequest.getPassword());
//        LoginResponse loginResponse = new LoginResponse(login.getAccessToken().getToken());
//        Cookie cookie = new Cookie("refresh_token", login.getAccessToken().getToken());
//
//        cookie.setMaxAge(3600);
//        cookie.setHttpOnly(true);
//        cookie.setPath("/api-polo");
//        response.addCookie(cookie);
//
    }

    @PostMapping("/forgot")
    public ForgotResponse forgot(@RequestBody ForgotRequest forgotRequest, HttpServletRequest request){
        var originUrl = request.getHeader("Origin");

        authService.forgot(forgotRequest.getEmail(), originUrl);

        return new ForgotResponse("Confira seu email");
    }

    @PostMapping("/logout")
    public LogoutResponse logout(HttpServletResponse response){
        Cookie cookie = new Cookie("refresh_token", null);

        cookie.setMaxAge(0);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);

        // TODO: remover o token após logout

        return new LogoutResponse("success");
    }

    @GetMapping("/auth")
    public UserResponse user(HttpServletRequest request){
        User user = (User) request.getAttribute("user");
        return new UserResponse(user);
    }

    @GetMapping("/list")
    public ResponseEntity<Object> listUser()  {
        return new ResponseEntity<>(authService.listUser(), HttpStatus.OK);
    }


    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteUser(@RequestParam("id") Long id)  {
        authService.deleteTalentCard(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
