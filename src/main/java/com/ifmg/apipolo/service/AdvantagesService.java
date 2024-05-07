package com.ifmg.apipolo.service;

import com.ifmg.apipolo.entity.AdvantagesCard;
import com.ifmg.apipolo.repository.AdvantagesRepository;
import com.ifmg.apipolo.vo.AdvantagesVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdvantagesService {

    @Autowired
    private AdvantagesRepository advantagesRepository;

    public List<AdvantagesVO> list(){

        List<AdvantagesVO> listVO = new ArrayList<>();
        List<AdvantagesCard> list = advantagesRepository.findAll();

        for(AdvantagesCard adv : list)
            listVO.add(new AdvantagesVO(adv));

        return listVO;
    }
}
