package com.ifmg.apipolo.service;

import com.ifmg.apipolo.entity.Image;
import com.ifmg.apipolo.entity.New;
import com.ifmg.apipolo.repository.ImageRepository;
import com.ifmg.apipolo.repository.MainNewCardRepository;
import com.ifmg.apipolo.repository.NewRepository;
import com.ifmg.apipolo.vo.NewVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NewService {

    @Autowired
    private NewRepository newRepository;

    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private MainNewCardRepository mainNewCardRepository;

    public void createNew(NewVO newVO) {

        New newNew = new New();

        newNew.setTitle(newVO.getTitle());
        newNew.setDate(newVO.getDate());
        newNew.setImg1(newVO.getImg1());
        newNew.setImg2(newVO.getImg2());
        newNew.setParagraph1(newVO.getParagraph1());
        newNew.setParagraph2(newVO.getParagraph2());

        newRepository.save(newNew);
    }

    public void updateNew(NewVO newVO) {

        Image image1 = imageRepository.getReferenceById(newVO.getImg1().getId());
        Image image2 = imageRepository.getReferenceById(newVO.getImg2().getId());
        New newNew = newRepository.getReferenceById(newVO.getId());

        newNew.setTitle(newVO.getTitle());
        newNew.setParagraph1(newVO.getParagraph1());
        newNew.setParagraph1(newVO.getParagraph2());
        newNew.setDate(newNew.getDate());
        image1.setCode(newVO.getImg1().getCode());
        image2.setCode(newVO.getImg2().getCode());

        imageRepository.save(image1);
        imageRepository.save(image2);
        newRepository.save(newNew);
    }

    public NewVO getOne(Long id){
        return new NewVO(newRepository.getReferenceById(id));
    }

    public List<NewVO> listThree(){

        List<NewVO> listVO = new ArrayList<>();
        List<New> list = newRepository.getLatestThree();

        for(New newNew : list)
            listVO.add(new NewVO(newNew));

        return listVO;
    }

    public List<NewVO> list(){

        List<NewVO> listVO = new ArrayList<>();
        List<New> list = newRepository.findAll();

        for(New newNew : list)
            listVO.add(new NewVO(newNew));

        return listVO;
    }

    public void deleteNew(Long id) {
        newRepository.deleteById(id);
    }
}
