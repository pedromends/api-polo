package com.ifmg.apipolo.service;

import com.ifmg.apipolo.entity.PresentationCard;
import com.ifmg.apipolo.repository.PresentationCardRepository;
import com.ifmg.apipolo.vo.PresentationCardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PresentationCardService {

    @Autowired
    private PresentationCardRepository presentationCardRepository;

    public List<PresentationCardVO> list(){

        List<PresentationCardVO> listVO = new ArrayList<>();
        List<PresentationCard> list = presentationCardRepository.findAll();

        for(PresentationCard presentationCard : list)
            listVO.add(new PresentationCardVO(presentationCard));

        return listVO;
    }
}