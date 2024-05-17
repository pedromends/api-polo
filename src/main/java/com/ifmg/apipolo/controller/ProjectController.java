package com.ifmg.apipolo.controller;

import com.ifmg.apipolo.service.ProjectService;
import com.ifmg.apipolo.vo.ProjectVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/project")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @PostMapping("/create")
    public ResponseEntity<Object> createProject(@RequestBody ProjectVO projectVO) {
        projectService.createProject(projectVO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<Object> listAdvantages()  {
        return new ResponseEntity<>(projectService.list(), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateProject(@RequestBody ProjectVO projectVO)  {
        projectService.updateProject(projectVO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping
    public void deleteProject(Long id){
        projectService.deleteProject(id);
    }
}
