package com.ifmg.apipolo.service;

import com.ifmg.apipolo.entity.Image;
import com.ifmg.apipolo.entity.New;
import com.ifmg.apipolo.repository.ImageRepository;
import com.ifmg.apipolo.repository.MainNewCardRepository;
import com.ifmg.apipolo.repository.NewRepository;
import com.ifmg.apipolo.vo.NewVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

        if (newVO.getImg1().getId() != null) {
            image1.setCode(newVO.getImg1().getCode());
            imageRepository.save(image1);
        }

        if (newVO.getImg2().getId() != null) {
            image2.setCode(newVO.getImg2().getCode());
            imageRepository.save(image2);
        }

        if(newVO.getTitle() != null)
            newNew.setTitle(newVO.getTitle());

        if(newVO.getParagraph1() != null)
            newNew.setParagraph1(newVO.getParagraph1());

        if(newVO.getParagraph2() != null)
            newNew.setParagraph1(newVO.getParagraph2());

        if(newNew.getDate() != null)
            newNew.setDate(newNew.getDate());

        newRepository.save(newNew);
    }

    public NewVO getOne(Long id){
        return new NewVO(newRepository.getReferenceById(id));
    }

    public List<NewVO> listPosts(Pageable pageable){

        List<NewVO> listVO = new ArrayList<>();
        List<New> list = newRepository.getLatestThree();

        for(New newNew : list)
            listVO.add(new NewVO(newNew));

        return listVO;
    }

    public Page<New> list(Pageable pageable){

        return newRepository.findAllDesc(pageable);
    }

    public void deleteNew(Long id) {
        newRepository.deleteById(id);
    }
}
