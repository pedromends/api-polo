package com.ifmg.apipolo.service;

import com.ifmg.apipolo.entity.Modality;
import com.ifmg.apipolo.repository.ModalityRepository;
import com.ifmg.apipolo.vo.ModalityVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ModalityService {

    @Autowired
    private ModalityRepository modalityRepository;

    public void createModality(ModalityVO modalityVO) {
        Modality modality = new Modality();

        modality.setName(modalityVO.getName());

        modalityRepository.save(modality);
    }

    public List<ModalityVO> list(){

        List<ModalityVO> modalityVO = new ArrayList<>();
        List<Modality> list = modalityRepository.findAll();

        for(Modality modality : list)
            modalityVO.add(new ModalityVO(modality));

        return modalityVO;
    }

    public void updateModality(ModalityVO modalityVO) {
        Modality modality = modalityRepository.getReferenceById(modalityVO.getId());

        modality.setName(modalityVO.getName());

        modalityRepository.save(modality);
    }

    public void deleteModality(Long id) {
        modalityRepository.deleteById(id);
    }
}
