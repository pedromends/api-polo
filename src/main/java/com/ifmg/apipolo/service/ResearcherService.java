package com.ifmg.apipolo.service;

import com.ifmg.apipolo.entity.Researcher;
import com.ifmg.apipolo.repository.ResearcherRepository;
import com.ifmg.apipolo.vo.ResearcherVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResearcherService {

    @Autowired
    private ResearcherRepository researcherRepository;

    public List<ResearcherVO> list(){

        List<ResearcherVO> listVO = new ArrayList<>();
        List<Researcher> list = researcherRepository.findAll();

        for(Researcher researcher : list)
            listVO.add(new ResearcherVO(researcher));

        return listVO;
    }
}
