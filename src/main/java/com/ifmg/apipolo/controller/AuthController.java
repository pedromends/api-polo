package com.ifmg.apipolo.controller;

import com.ifmg.apipolo.entity.ForgotRequest;
import com.ifmg.apipolo.entity.ForgotResponse;
import com.ifmg.apipolo.record.LoginResponse;
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
import sendinblue.ApiException;

import java.util.List;


@CrossOrigin("*")
@RestController
@RequestMapping("/user")
public class AuthController {

    @Autowired
    AuthService authService;

    @GetMapping("/hello")
    public ResponseEntity<String> helloWorld()  {
        return new ResponseEntity<>("Hello World", HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<LoginResponse> createUser(@RequestBody UserVO userVO) throws ApiException {
        return new ResponseEntity<>(authService.registerUser(userVO), HttpStatus.OK);
    }

    @PostMapping("/refresh")
    public ResponseEntity<RefreshResponse> refresh(@CookieValue("refresh_token") String refreshToken, HttpServletResponse response){
        String currToken = authService.refreshAccess(refreshToken).getCurrentAccess();

        Cookie cookie = new Cookie("refresh_token", currToken);

        response.addCookie(cookie);
        return new ResponseEntity<>(new RefreshResponse(currToken), HttpStatus.OK);
    }

    @GetMapping("/get-info")
    public ResponseEntity<UserVO> getOneUser(@RequestParam String email){
        UserVO userInfo = authService.getOneUser(email);
        return new ResponseEntity<>(userInfo, HttpStatus.OK);
    }

    @GetMapping("/handshake")
    public ResponseEntity<String> handshake(){
        return new ResponseEntity<>("Shall we start ?", HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginRequest(@RequestBody LoginRequest loginRequest)  {
        return new ResponseEntity<String>(authService.generateToken(loginRequest).getBody(), HttpStatus.OK);
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

        return new LogoutResponse("success");
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateUser(@RequestBody UserVO userVO)  {
        authService.updateUser(userVO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/change-permissions")
    public ResponseEntity<Object> changePermissions(@RequestBody List<UserVO> userVO, String permission)  {
        authService.changeUserPermissions(userVO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<Object> listUser()  {
        return new ResponseEntity<>(authService.listUser(), HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable("id") Long id)  {
        authService.deleteUser(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
