package com.ifmg.apipolo.service;

import com.ifmg.apipolo.entity.TalentCard;
import com.ifmg.apipolo.repository.TalentRepository;
import com.ifmg.apipolo.vo.TalentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TalentService {

    @Autowired
    private TalentRepository talentRepository;

    public List<TalentVO> list(){

        List<TalentVO> listVO = new ArrayList<>();
        List<TalentCard> list = talentRepository.findAll();

        for(TalentCard talentCard : list)
            listVO.add(new TalentVO(talentCard));

        return listVO;
    }
}
