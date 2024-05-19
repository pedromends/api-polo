package com.ifmg.apipolo.service;

import com.ifmg.apipolo.entity.AdvantagesCard;
import com.ifmg.apipolo.entity.Image;
import com.ifmg.apipolo.repository.AdvantagesCardRepository;
import com.ifmg.apipolo.repository.ImageRepository;
import com.ifmg.apipolo.vo.AdvantagesCardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdvantagesCardService {

    @Autowired
    private AdvantagesCardRepository advantagesCardRepository;

    @Autowired
    private ImageRepository imageRepository;

//    public void createCard(AdvantagesCardVO advantagesCardVO) {
//
//        Image cardImage = imageRepository.getReferenceById(advantagesCardVO.getImg().getId());
//        AdvantagesCard advantagesCard = advantagesCardRepository.getReferenceById(advantagesCardVO.getId());
//
//        advantagesCard.setDifferential(advantagesCardVO.getDifferential());
//        advantagesCard.setDescription(advantagesCardVO.getDescription());
//        cardImage.setCode(advantagesCardVO.getImg().getCode());
//
//        imageRepository.save(cardImage);
//        advantagesCardRepository.save(advantagesCard);
//    }

    public void updateCard(AdvantagesCardVO advantagesCardVO) {
        Image cardImage = imageRepository.getReferenceById(advantagesCardVO.getImg().getId());
        AdvantagesCard advantagesCard = advantagesCardRepository.getReferenceById(advantagesCardVO.getId());

        advantagesCard.setDifferential(advantagesCardVO.getDifferential());
        advantagesCard.setDescription(advantagesCardVO.getDescription());
        cardImage.setCode(advantagesCardVO.getImg().getCode());

        imageRepository.save(cardImage);
        advantagesCardRepository.save(advantagesCard);
    }

    public List<AdvantagesCardVO> list(){

        List<AdvantagesCardVO> listVO = new ArrayList<>();
        List<AdvantagesCard> list = advantagesCardRepository.findAll();

        for(AdvantagesCard adv : list)
            listVO.add(new AdvantagesCardVO(adv));

        return listVO;
    }

    public void delete(Long id) {
        AdvantagesCard advantagesCard = advantagesCardRepository.getReferenceById(id);
        Image cardImage = imageRepository.getReferenceById(advantagesCard.getImg().getId());

        imageRepository.delete(cardImage);
        advantagesCardRepository.deleteById(id);
    }
}
