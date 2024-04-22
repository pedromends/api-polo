package com.ifmg.apipolo.service;

import com.ifmg.apipolo.entity.Researcher;
import com.ifmg.apipolo.entity.Talent;
import com.ifmg.apipolo.repository.ResearcherRepository;
import com.ifmg.apipolo.repository.TalentRepository;
import com.ifmg.apipolo.vo.ResearcherVO;
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
        List<Talent> list = talentRepository.findAll();

        for(Talent talent : list)
            listVO.add(new TalentVO(talent));

        return listVO;
    }
}
