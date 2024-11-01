package com.ifmg.apipolo.controller;

import com.ifmg.apipolo.entity.ForgotRequest;
import com.ifmg.apipolo.entity.ForgotResponse;
import com.ifmg.apipolo.entity.Token;
import com.ifmg.apipolo.entity.User;
import com.ifmg.apipolo.login.MyCustomUserDetails;
import com.ifmg.apipolo.record.LoginResponse;
import com.ifmg.apipolo.repository.ImageRepository;
import com.ifmg.apipolo.repository.TokenRepository;
import com.ifmg.apipolo.repository.UserRepository;
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

import java.util.List;


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

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private ImageRepository imageRepository;

    @GetMapping("/hello")
    public ResponseEntity<String> helloWorld()  {
        return new ResponseEntity<>("Hello World", HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<UserVO> createUser(@RequestBody UserVO userVO)  {
        UserVO newUserVO = authService.registerUser(userVO);
        return new ResponseEntity<>(newUserVO, HttpStatus.OK);
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

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest)  {

        try{
            UserVO userReturn = new UserVO();

            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
            Authentication authentication = authenticationManager.authenticate(authToken);

            MyCustomUserDetails userDetailsService = customUserDetailsService.loadUserByUsername(loginRequest.getEmail());
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String tokenCode = jwtTokenService.generateToken(userDetailsService);
            User user = userRepository.findByUsername(loginRequest.getEmail());

            var newUserTk = tokenRepository.findByUserId(user.getId());

            userReturn.setEmail(user.getEmail());
            userReturn.setFirstName(user.getFirstName());
            userReturn.setLastName(user.getLastName());
            userReturn.setRole(user.getRole());

            if(newUserTk == null){
                Token newToken = new Token(user, tokenCode);
                tokenRepository.save(newToken);
            } else {
                newUserTk.setToken(tokenCode);
                tokenRepository.save(newUserTk);
            }

            return new ResponseEntity<>(new LoginResponse(userReturn, tokenCode), HttpStatus.OK);
        }catch(AuthenticationException e){
            return new ResponseEntity<>(new LoginResponse(e.getMessage()), HttpStatus.UNAUTHORIZED);
        }
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


    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteUser(@RequestParam("id") Long id)  {
        authService.deleteTalentCard(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
