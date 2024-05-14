package com.ifmg.apipolo.controller;

import com.ifmg.apipolo.service.StudentService;
import com.ifmg.apipolo.vo.StudentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/create")
    public ResponseEntity<Object> createCard(@RequestBody StudentVO studentVO) {
        studentService.createStudent(studentVO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateCard(@RequestBody StudentVO studentVO) {
        studentService.updateStudent(studentVO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<Object> listAdvantages()  {
        return new ResponseEntity<>(studentService.list(), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public void deleteProject(Long id){
        studentService.deleteStudent(id);
    }

}
