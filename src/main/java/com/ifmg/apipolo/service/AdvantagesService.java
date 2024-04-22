package com.ifmg.apipolo.service;

import com.ifmg.apipolo.entity.Advantages;
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
        List<Advantages> list = advantagesRepository.findAll();

        for(Advantages adv : list)
            listVO.add(new AdvantagesVO(adv));

        return listVO;
    }
}
