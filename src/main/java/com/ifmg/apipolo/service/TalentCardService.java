package com.ifmg.apipolo.service;

import com.ifmg.apipolo.entity.Image;
import com.ifmg.apipolo.entity.TalentCard;
import com.ifmg.apipolo.repository.ImageRepository;
import com.ifmg.apipolo.repository.TalentCardRepository;
import com.ifmg.apipolo.vo.TalentCardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TalentCardService {

    @Autowired
    private TalentCardRepository talentCardRepository;

    @Autowired
    private ImageRepository imageRepository;

    public void createMainNew(TalentCardVO talentCardVO) {

        TalentCard talentCard = new TalentCard();

        talentCard.setName(talentCardVO.getName());
        talentCard.setProfession(talentCardVO.getProfession());
        talentCard.setDetails(talentCardVO.getDetails());
        talentCard.setImg(talentCardVO.getImg());

        talentCardRepository.save(talentCard);
    }

    public List<TalentCardVO> list(){

        List<TalentCardVO> listVO = new ArrayList<>();
        List<TalentCard> list = talentCardRepository.findAll();

        for(TalentCard talentCard : list)
            listVO.add(new TalentCardVO(talentCard));

        return listVO;
    }

    public void updateTalentCard(TalentCardVO talentCardVO) {

        TalentCard talentCard = talentCardRepository.getReferenceById(talentCardVO.getId());

        if(talentCardVO.getImg().getId() != null){
            Image cardImage = imageRepository.getReferenceById(talentCardVO.getImg().getId());
            cardImage.setCode(talentCardVO.getImg().getCode());

            imageRepository.save(cardImage);
        }

        if(talentCardVO.getProfession() != null)
            talentCard.setProfession(talentCardVO.getProfession());

        if(talentCardVO.getDetails() != null)
            talentCard.setName(talentCardVO.getName());

        if(talentCardVO.getDetails() != null)
            talentCard.setDetails(talentCardVO.getDetails());

        talentCardRepository.save(talentCard);
    }

    public void deleteTalentCard(Long id) {
        talentCardRepository.deleteById(id);
    }
}
