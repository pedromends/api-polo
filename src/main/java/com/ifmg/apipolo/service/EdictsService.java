package com.ifmg.apipolo.service;

import com.ifmg.apipolo.entity.Edicts;
import com.ifmg.apipolo.repository.EdictsRepository;
import com.ifmg.apipolo.vo.EdictsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class EdictsService {

    @Autowired
    private EdictsRepository edictsRepository;

    public ResponseEntity<String> uploadFile(MultipartFile file, String title){
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Por favor, selecione um arquivo para upload.");
        }

        try {
            Edicts newEdict = new Edicts();
            Path uploadsPath = Paths.get("C:\\ProgramData\\Kimeratech\\uploads");

            File uploadsDir = uploadsPath.toFile();

            if (!uploadsDir.exists()) {
                uploadsDir.mkdirs();
            }

            Path filePath = uploadsPath.resolve(file.getOriginalFilename());
            Files.write(filePath, file.getBytes());

            newEdict.setFilename(file.getOriginalFilename());
            newEdict.setTitle(title);

            edictsRepository.save(newEdict);
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
            Edicts newEdict = edictsRepository.getReferenceById(id);
            Path uploadsPath = Paths.get("C:\\ProgramData\\Kimeratech\\uploads");

            File uploadsDir = uploadsPath.toFile();

            if (!uploadsDir.exists()) {
                uploadsDir.mkdirs();
            }

            Path filePath = uploadsPath.resolve(file.getOriginalFilename());
            Files.write(filePath, file.getBytes());

            newEdict.setFilename(file.getOriginalFilename());
            newEdict.setTitle(title);

            edictsRepository.save(newEdict);

            return ResponseEntity.ok("Arquivo enviado com sucesso: " + file.getOriginalFilename());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Falha ao enviar o arquivo.");
        }
    }



    public void createEdicts(EdictsVO newEdicts) {
        Edicts newEdict = new Edicts();

        newEdict.setLink(newEdicts.getLink());
        newEdict.setTitle(newEdicts.getTitle());
        newEdict.setFilename(newEdicts.getFilename());

        edictsRepository.save(newEdict);
    }

    public List<EdictsVO> listEdicts() {

        List<EdictsVO> listVO = new ArrayList<>();
        List<Edicts> list = edictsRepository.findAll();

        for(Edicts edicts : list)
            listVO.add(new EdictsVO(edicts));

        return listVO;
    }

    public void updateEdicts(EdictsVO EdictsVO) {

        Edicts updatedEdict = edictsRepository.getReferenceById(EdictsVO.getId());
        updatedEdict.setLink(EdictsVO.getLink());
        updatedEdict.setTitle(EdictsVO.getTitle());

        edictsRepository.save(updatedEdict);
    }

    public void deleteEdicts(Long id) {
        edictsRepository.deleteById(id);
    }
}