package com.ifmg.apipolo.service;

import com.ifmg.apipolo.entity.MainNewCard;
import com.ifmg.apipolo.repository.MainNewRepository;
import com.ifmg.apipolo.vo.MainNewCardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MainNewService {

    @Autowired
    private MainNewRepository mainNewRepository;

    public Optional<MainNewCardVO> list(){

        Optional<MainNewCard> mainNew = mainNewRepository.findById(Long.valueOf(1));
        MainNewCardVO mainNewCardVO = new MainNewCardVO(mainNew.get());

        return Optional.of(mainNewCardVO);
    }
}
