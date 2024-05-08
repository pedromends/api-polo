package com.ifmg.apipolo.service;

import com.ifmg.apipolo.entity.TalentCard;
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

    public List<TalentCardVO> list(){

        List<TalentCardVO> listVO = new ArrayList<>();
        List<TalentCard> list = talentCardRepository.findAll();

        for(TalentCard talentCard : list)
            listVO.add(new TalentCardVO(talentCard));

        return listVO;
    }
}
