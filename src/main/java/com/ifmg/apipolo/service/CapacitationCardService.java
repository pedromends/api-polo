package com.ifmg.apipolo.service;

import com.ifmg.apipolo.entity.CapacitationCard;
import com.ifmg.apipolo.entity.Image;
import com.ifmg.apipolo.entity.MainNewCard;
import com.ifmg.apipolo.repository.CapacitationCardRepository;
import com.ifmg.apipolo.repository.ImageRepository;
import com.ifmg.apipolo.vo.CapacitationCardVO;
import com.ifmg.apipolo.vo.MainNewCardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CapacitationCardService {

    @Autowired
    private CapacitationCardRepository capacitationCardRepository;

    @Autowired
    private ImageRepository imageRepository;

    public void createMainNew(CapacitationCardVO capacitationCardVO) {

        Image cardImage = new Image();
        CapacitationCard capacitationCard = new CapacitationCard();

        cardImage.setName(capacitationCardVO.getImg().getName());
        cardImage.setCode(capacitationCardVO.getImg().getCode());

        capacitationCard.setTitle(capacitationCardVO.getTitle());
        capacitationCard.setSubtitle(capacitationCardVO.getSubtitle());
        cardImage.setCode(capacitationCardVO.getImg().getCode());

        imageRepository.save(cardImage);
        capacitationCardRepository.save(capacitationCard);
    }

    public List<CapacitationCardVO> list(){

        List<CapacitationCardVO> listVO = new ArrayList<>();
        List<CapacitationCard> list = capacitationCardRepository.findAll();

        for(CapacitationCard capacitationCard : list)
            listVO.add(new CapacitationCardVO(capacitationCard));

        return listVO;
    }

    public void updateMainNew(CapacitationCardVO capacitationCardVO) {

        Image cardImage = imageRepository.getReferenceById(capacitationCardVO.getImg().getId());
        CapacitationCard capacitationCard = capacitationCardRepository.getReferenceById(capacitationCardVO.getId());

        capacitationCard.setTitle(capacitationCardVO.getTitle());
        capacitationCard.setSubtitle(capacitationCardVO.getSubtitle());
        cardImage.setCode(capacitationCardVO.getImg().getCode());

        imageRepository.save(cardImage);
        capacitationCardRepository.save(capacitationCard);
    }

    public void deleteCapacitation(Long id) {
        capacitationCardRepository.deleteById(id);
    }
}
