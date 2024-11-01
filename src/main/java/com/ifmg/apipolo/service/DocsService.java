package com.ifmg.apipolo.service;

import com.ifmg.apipolo.entity.Docs;
import com.ifmg.apipolo.repository.DocsRepository;
import com.ifmg.apipolo.vo.DocsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class DocsService {

    @Autowired
    private DocsRepository docsRepository;

    public ResponseEntity<String> uploadFile(MultipartFile file, String title){
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Por favor, selecione um arquivo para upload.");
        }

        try {
            Docs newDoc = new Docs();
            Path uploadsPath = Paths.get("C:\\ProgramData\\Kimeratech\\uploads");

            File uploadsDir = uploadsPath.toFile();

            if (!uploadsDir.exists()) {
                uploadsDir.mkdirs();
            }

            Path filePath = uploadsPath.resolve(file.getOriginalFilename());
            Files.write(filePath, file.getBytes());

            newDoc.setFilename(file.getOriginalFilename());
            newDoc.setTitle(title);

            docsRepository.save(newDoc);
            return ResponseEntity.ok("Arquivo enviado com sucesso: " + file.getOriginalFilename());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Falha ao enviar o arquivo.");
        }
    }

    public ResponseEntity<String> updateFile(MultipartFile file, String title, Long id){
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Por favor, selecione um arquivo para upload.");
        }

        try {
            Docs newDoc = docsRepository.getReferenceById(id);
            Path uploadsPath = Paths.get("C:\\ProgramData\\Kimeratech\\uploads");

            File uploadsDir = uploadsPath.toFile();

            if (!uploadsDir.exists()) {
                uploadsDir.mkdirs();
            }

            Path filePath = uploadsPath.resolve(file.getOriginalFilename());
            Files.write(filePath, file.getBytes());

            newDoc.setFilename(file.getOriginalFilename());
            newDoc.setTitle(title);

            docsRepository.save(newDoc);
            return ResponseEntity.ok("Arquivo enviado com sucesso: " + file.getOriginalFilename());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Falha ao enviar o arquivo.");
        }
    }



    public void createDocs(DocsVO docsVO) {
        Docs docs = new Docs();

        docs.setLink(docsVO.getLink());
        docs.setTitle(docsVO.getTitle());
        docs.setFilename(docsVO.getFilename());

        docsRepository.save(docs);
    }

    public List<DocsVO> listDocs() {

        List<DocsVO> listVO = new ArrayList<>();
        List<Docs> list = docsRepository.findAll();

        for(Docs Docs : list)
            listVO.add(new DocsVO(Docs));

        return listVO;
    }

    public void updateDocs(DocsVO DocsVO) {

        Docs docs = docsRepository.getReferenceById(DocsVO.getId());
        docs.setLink(DocsVO.getLink());
        docs.setTitle(DocsVO.getTitle());

        docsRepository.save(docs);
    }

    public void deleteDocs(Long id) {
        docsRepository.deleteById(id);
    }
}
