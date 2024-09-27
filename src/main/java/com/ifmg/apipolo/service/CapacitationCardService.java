package com.ifmg.apipolo.service;

import com.ifmg.apipolo.entity.CapacitationCard;
import com.ifmg.apipolo.entity.CapacitationText;
import com.ifmg.apipolo.entity.Image;
import com.ifmg.apipolo.entity.MainNewCard;
import com.ifmg.apipolo.repository.CapacitationCardRepository;
import com.ifmg.apipolo.repository.CapacitationRepository;
import com.ifmg.apipolo.repository.ImageRepository;
import com.ifmg.apipolo.vo.CapacitationCardVO;
import com.ifmg.apipolo.vo.CapacitationTextVO;
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
    private CapacitationRepository capacitationRepository;

    @Autowired
    private ImageRepository imageRepository;

    public List<CapacitationCardVO> list(){

        List<CapacitationCardVO> listVO = new ArrayList<>();
        List<CapacitationCard> list = capacitationCardRepository.findAll();

        for(CapacitationCard capacitationCard : list)
            listVO.add(new CapacitationCardVO(capacitationCard));

        return listVO;
    }

    public void updateMainNew(CapacitationCardVO capacitationCardVO) {

        CapacitationCard capacitationCard = capacitationCardRepository.getReferenceById(capacitationCardVO.getId());

        if(capacitationCardVO.getTitle() != null)
            capacitationCard.setTitle(capacitationCardVO.getTitle());

        if(capacitationCardVO.getImg().getId() != null){
            Image cardImage = imageRepository.getReferenceById(capacitationCardVO.getImg().getId());
            cardImage.setCode(capacitationCardVO.getImg().getCode());

            imageRepository.save(cardImage);
        }

        if(capacitationCardVO.getSubtitle() != null)
            capacitationCard.setSubtitle(capacitationCardVO.getSubtitle());

        capacitationCardRepository.save(capacitationCard);
    }

    public void updateParag(String newText) {
        CapacitationText capacitationText = capacitationRepository.getReferenceById(1L);
        capacitationText.setText(newText);
        capacitationRepository.save(capacitationText);
    }

    public CapacitationTextVO getParagraph() {
        System.out.println(capacitationRepository.getReferenceById(1L));
        return new CapacitationTextVO(capacitationRepository.getReferenceById(1L));
    }
}
