package com.ifmg.apipolo.service;

import com.ifmg.apipolo.entity.PresenterCard;
import com.ifmg.apipolo.repository.PresenterCardRepository;
import com.ifmg.apipolo.vo.PresenterCardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PresenterCardService {

    @Autowired
    private PresenterCardRepository presenterCardRepository;

    public List<PresenterCardVO> list(){

        List<PresenterCardVO> listVO = new ArrayList<>();
        List<PresenterCard> list = presenterCardRepository.findAll();

        for(PresenterCard presenterCard : list)
            listVO.add(new PresenterCardVO(presenterCard));

        return listVO;
    }
}
