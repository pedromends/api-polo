package com.ifmg.apipolo.controller;

import com.ifmg.apipolo.service.UserService;
import com.ifmg.apipolo.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/create")
    public ResponseEntity<Object> createUser(@RequestBody UserVO userVO)  {
        userService.createUser(userVO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<Object> listUser()  {
        return new ResponseEntity<>(userService.listUser(), HttpStatus.OK);
    }

    @PutMapping("/list")
    public ResponseEntity<Object> updateUser(@RequestBody UserVO userVO)  {
        userService.updateUser(userVO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/list")
    public ResponseEntity<Object> deleteUser(@RequestParam("id") Long id)  {
        userService.deleteTalentCard(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
