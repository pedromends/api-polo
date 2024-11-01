package com.ifmg.apipolo.service;

import com.ifmg.apipolo.entity.PresentationCard;
import com.ifmg.apipolo.repository.PresentationCardRepository;
import com.ifmg.apipolo.vo.PresentationCardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PresentationCardService {

    @Autowired
    private PresentationCardRepository presentationCardRepository;

    public void createCard(PresentationCardVO presentationCardVO) {
        PresentationCard presentationCard = new PresentationCard();

        presentationCard.setNum(presentationCardVO.getNum());
        presentationCard.setText(presentationCardVO.getText());

        presentationCardRepository.save(presentationCard);
    }

    public List<PresentationCardVO> list(){

        List<PresentationCardVO> listVO = new ArrayList<>();
        List<PresentationCard> list = presentationCardRepository.findAll();

        for(PresentationCard presentationCard : list)
            listVO.add(new PresentationCardVO(presentationCard));

        return listVO;
    }

    public void updatePresentationCard(PresentationCardVO presentationCardVO) {
        PresentationCard presentationCard = presentationCardRepository.getReferenceById(presentationCardVO.getId());

        if(presentationCardVO.getNum() > 0)
            presentationCard.setNum(presentationCardVO.getNum());

        if(presentationCardVO.getText() != null)
            presentationCard.setText(presentationCardVO.getText());

        presentationCardRepository.save(presentationCard);
    }

    public void deletePresentationCard(Long id){
        presentationCardRepository.deleteById(id);
    }
}
