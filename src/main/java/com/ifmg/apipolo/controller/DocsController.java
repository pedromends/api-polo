package com.ifmg.apipolo.controller;

import com.ifmg.apipolo.service.DocsService;
import com.ifmg.apipolo.service.EdictsService;
import com.ifmg.apipolo.vo.DocsVO;
import com.ifmg.apipolo.vo.EdictsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
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
    public ResponseEntity<String> createDocs(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Por favor, selecione um arquivo para upload.");
        }

        try {
            // Define o caminho para a pasta de uploads
            Path uploadsPath = Paths.get("C:\\ProgramData\\Kimeratech\\uploads");

            // Obtenha o arquivo de destino
            File uploadsDir = uploadsPath.toFile();

            // Criar a pasta se ela não existir
            if (!uploadsDir.exists()) {
                uploadsDir.mkdirs();
            }

            // Define o caminho completo para o novo arquivo (com o nome do arquivo original)
            Path filePath = uploadsPath.resolve(file.getOriginalFilename());

            // Escreve o arquivo no diretório uploads
            Files.write(filePath, file.getBytes());
            return ResponseEntity.ok("Arquivo enviado com sucesso: " + file.getOriginalFilename());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Falha ao enviar o arquivo.");
        }
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
