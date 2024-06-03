package com.ifmg.apipolo.controller;

import com.ifmg.apipolo.entity.User;
import com.ifmg.apipolo.service.AuthService;
import com.ifmg.apipolo.service.LogoutResponse;
import com.ifmg.apipolo.vo.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/user")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<Optional<User>> createUser(@RequestBody UserVO userVO)  {
        return new ResponseEntity<>(authService.registerUser(userVO), HttpStatus.OK);
    }

    @PostMapping("/refresh")
    public ResponseEntity<RefreshResponse> refresh(@CookieValue("refresh_token")String refreshToken, HttpServletResponse response){
        String currToken = authService.refreshAccess(refreshToken).getCurrentAccess();
        Cookie cookie = new Cookie("refresh_token",currToken );
        response.addCookie(cookie);
        return new ResponseEntity<>(new RefreshResponse(currToken), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest, HttpServletResponse response)  {
        var login = authService.login(loginRequest.getEmail(), loginRequest.getPassword());

        Cookie cookie = new Cookie("refresh_token", login.getAccessToken().getToken());
        cookie.setMaxAge(3600);
        cookie.setHttpOnly(true);
        cookie.setPath("/api-polo");

        response.addCookie(cookie);
        LoginResponse loginResponse = new LoginResponse(login.getAccessToken().getToken());
        return new ResponseEntity<>(loginResponse, HttpStatus.OK);
    }

    @PostMapping("/logout")
    public LogoutResponse logout(HttpServletResponse response){
        Cookie cookie = new Cookie("refresh_token", null);
        cookie.setMaxAge(0);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);

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
