package com.ifmg.apipolo.controller;

import com.ifmg.apipolo.service.DocsService;
import com.ifmg.apipolo.service.EdictsService;
import com.ifmg.apipolo.vo.DocsVO;
import com.ifmg.apipolo.vo.EdictsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@CrossOrigin("*")
@RestController
@RequestMapping("/docs")
public class DocsController {

    @Autowired
    DocsService docsService;

    @PostMapping("/create")
    public ResponseEntity<String> createDocs(@RequestParam("file") MultipartFile file, @RequestParam("title") String title) {
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Por favor, selecione um arquivo para upload.");
        }

        try {
            DocsVO docVO = new DocsVO();

            Path uploadsPath = Paths.get("C:\\ProgramData\\Kimeratech\\uploads");

            File uploadsDir = uploadsPath.toFile();

            if (!uploadsDir.exists()) {
                uploadsDir.mkdirs();
            }

            Path filePath = uploadsPath.resolve(file.getOriginalFilename());
            Files.write(filePath, file.getBytes());

            docVO.setFilename(file.getOriginalFilename());
            docVO.setTitle(title);

            docsService.createDocs(docVO);
            return ResponseEntity.ok("Arquivo enviado com sucesso: " + file.getOriginalFilename());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Falha ao enviar o arquivo.");
        }
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
    public ResponseEntity<Object> updateDocs(@RequestBody DocsVO docsVO) {
        docsService.updateDocs(docsVO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<Object> listDocs()  {
        return new ResponseEntity<>(docsService.listDocs(), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteEdicts(@RequestParam("id") Long id)  {
        docsService.deleteDocs(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
