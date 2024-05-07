package com.ifmg.apipolo.service;

import com.ifmg.apipolo.entity.AdvantagesCard;
import com.ifmg.apipolo.repository.AdvantagesRepository;
import com.ifmg.apipolo.vo.AdvantagesCardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdvantagesService {

    @Autowired
    private AdvantagesRepository advantagesRepository;

    public List<AdvantagesCardVO> list(){

        List<AdvantagesCardVO> listVO = new ArrayList<>();
        List<AdvantagesCard> list = advantagesRepository.findAll();

        for(AdvantagesCard adv : list)
            listVO.add(new AdvantagesCardVO(adv));

        return listVO;
    }
}
