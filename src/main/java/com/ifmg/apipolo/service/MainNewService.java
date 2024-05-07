package com.ifmg.apipolo.service;

import com.ifmg.apipolo.entity.MainNewCard;
import com.ifmg.apipolo.repository.MainNewRepository;
import com.ifmg.apipolo.vo.MainNewVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MainNewService {

    @Autowired
    private MainNewRepository mainNewRepository;

    public Optional<MainNewVO> list(){

        Optional<MainNewCard> mainNew = mainNewRepository.findById(Long.valueOf(1));
        MainNewVO mainNewVO = new MainNewVO(mainNew.get());

        return Optional.of(mainNewVO);
    }
}
