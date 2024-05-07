package com.ifmg.apipolo.service;

import com.ifmg.apipolo.entity.CapacitationCard;
import com.ifmg.apipolo.repository.CapacitationRepository;
import com.ifmg.apipolo.vo.CapacitationCardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CapacitationService {

    @Autowired
    private CapacitationRepository capacitationRepository;

    public List<CapacitationCardVO> list(){

        List<CapacitationCardVO> listVO = new ArrayList<>();
        List<CapacitationCard> list = capacitationRepository.findAll();

        for(CapacitationCard capacitationCard : list)
            listVO.add(new CapacitationCardVO(capacitationCard));

        return listVO;
    }
}
