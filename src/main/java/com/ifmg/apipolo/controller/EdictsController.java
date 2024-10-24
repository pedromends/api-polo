package com.ifmg.apipolo.controller;

import com.ifmg.apipolo.service.EdictsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@CrossOrigin("*")
@RestController
@RequestMapping("/edicts")
public class EdictsController {

    @Autowired
    EdictsService edictsService;

    @PostMapping("/create")
    public ResponseEntity<String> createEdicts(@RequestParam("file") MultipartFile file, @RequestParam("title") String title) {
        return edictsService.uploadFile(file, title);
    }

    @GetMapping("/download/{filename}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) throws IOException {
        Path filePath = Paths.get("C:\\ProgramData\\Kimeratech\\uploads").resolve(filename);
        Resource resource = new PathResource(filePath);

        if (!resource.exists()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF) // Define o tipo de arquivo
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"") // Define o cabeçalho para download
                .body(resource);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateEdicts(@RequestParam("file") MultipartFile file, @RequestParam("title") String title, @RequestParam("id") Long id) {
        return edictsService.updateFile(file, title, id);
    }

    @GetMapping("/list")
    public ResponseEntity<Object> listEdicts()  {
        return new ResponseEntity<>(edictsService.listEdicts(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteEdicts(@PathVariable("id") Long id)  {
        edictsService.deleteEdicts(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
