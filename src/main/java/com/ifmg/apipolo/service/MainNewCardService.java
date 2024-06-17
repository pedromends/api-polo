package com.ifmg.apipolo.service;

import com.ifmg.apipolo.entity.AdvantagesCard;
import com.ifmg.apipolo.entity.Image;
import com.ifmg.apipolo.entity.MainNewCard;
import com.ifmg.apipolo.repository.ImageRepository;
import com.ifmg.apipolo.repository.MainNewCardRepository;
import com.ifmg.apipolo.vo.AdvantagesCardVO;
import com.ifmg.apipolo.vo.MainNewCardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MainNewCardService {

    @Autowired
    private MainNewCardRepository mainNewCardRepository;

    @Autowired
    private ImageRepository imageRepository;

    public void createMainNew(MainNewCardVO mainNewCardVO) {

        MainNewCard mainNewCard = mainNewCardRepository.getReferenceById(mainNewCardVO.getId());
        Image cardImage = new Image();

        mainNewCard.setTip(mainNewCardVO.getTip());
        mainNewCard.setTitle(mainNewCardVO.getTitle());
        mainNewCard.setParagraph(mainNewCardVO.getParagraph());
        mainNewCard.setImg(mainNewCardVO.getImage());

        imageRepository.save(cardImage);
        mainNewCardRepository.save(mainNewCard);
    }

    public Optional<MainNewCardVO> list(){

        Optional<MainNewCard> mainNew = mainNewCardRepository.findById(Long.valueOf(1));
        MainNewCardVO mainNewCardVO = new MainNewCardVO(mainNew.get());

        return Optional.of(mainNewCardVO);
    }

    public void updateMainNew(MainNewCardVO mainNewCardVO) {

        MainNewCard mainNewCard = mainNewCardRepository.getReferenceById(mainNewCardVO.getId());

        if(mainNewCardVO.getImage().getId() != null){
            Image cardImage = imageRepository.getReferenceById(mainNewCardVO.getImage().getId());
            cardImage.setCode(mainNewCardVO.getImage().getCode());

            imageRepository.save(cardImage);
        }

        if(mainNewCardVO.getTip() != null)
            mainNewCard.setTip(mainNewCardVO.getTip());

        if(mainNewCardVO.getTitle() != null)
            mainNewCard.setTitle(mainNewCardVO.getTitle());

        if(mainNewCardVO.getParagraph() != null)
            mainNewCard.setParagraph(mainNewCardVO.getParagraph());

        mainNewCardRepository.save(mainNewCard);
    }

    public void deleteMainNewService(Long id) {
        mainNewCardRepository.deleteById(id);
    }
}
