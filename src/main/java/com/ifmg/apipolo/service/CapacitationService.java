package com.ifmg.apipolo.service;

import com.ifmg.apipolo.entity.Capacitation;
import com.ifmg.apipolo.repository.CapacitationRepository;
import com.ifmg.apipolo.vo.CapacitationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CapacitationService {

    @Autowired
    private CapacitationRepository capacitationRepository;

    public List<CapacitationVO> list(){

        List<CapacitationVO> listVO = new ArrayList<>();
        List<Capacitation> list = capacitationRepository.findAll();

        for(Capacitation capacitation : list)
            listVO.add(new CapacitationVO(capacitation));

        return listVO;
    }
}
