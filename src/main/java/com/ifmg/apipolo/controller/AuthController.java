package com.ifmg.apipolo.controller;

import com.ifmg.apipolo.entity.User;
import com.ifmg.apipolo.service.AuthService;
import com.ifmg.apipolo.vo.LoginRequest;
import com.ifmg.apipolo.vo.LoginResponse;
import com.ifmg.apipolo.vo.UserResponse;
import com.ifmg.apipolo.vo.UserVO;
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

    @GetMapping("/auth")
    public UserResponse user(HttpServletRequest request){
        User user = (User) request.getAttribute("user");
        return new UserResponse(user);
    }

    @GetMapping("/list")
    public ResponseEntity<Object> listUser()  {
        return new ResponseEntity<>(authService.listUser(), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateUser(@RequestBody UserVO userVO)  {
        authService.updateUser(userVO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteUser(@RequestParam("id") Long id)  {
        authService.deleteTalentCard(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
