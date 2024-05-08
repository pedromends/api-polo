package com.ifmg.apipolo.service;

import com.ifmg.apipolo.entity.MainNewCard;
import com.ifmg.apipolo.repository.MainNewCardRepository;
import com.ifmg.apipolo.vo.MainNewCardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MainNewCardService {

    @Autowired
    private MainNewCardRepository mainNewCardRepository;

    public Optional<MainNewCardVO> list(){

        Optional<MainNewCard> mainNew = mainNewCardRepository.findById(Long.valueOf(1));
        MainNewCardVO mainNewCardVO = new MainNewCardVO(mainNew.get());

        return Optional.of(mainNewCardVO);
    }
}
