package com.ifmg.apipolo.service;

import com.ifmg.apipolo.entity.Image;
import com.ifmg.apipolo.entity.MainNew;
import com.ifmg.apipolo.repository.ImageRepository;
import com.ifmg.apipolo.repository.MainNewRepository;
import com.ifmg.apipolo.vo.ImageVO;
import com.ifmg.apipolo.vo.MainNewVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MainNewService {

    @Autowired
    private MainNewRepository mainNewRepository;

    public List<MainNewVO> list(){

        List<MainNewVO> listVO = new ArrayList<>();
        List<MainNew> list = mainNewRepository.findAll();

        for(MainNew mainNew : list)
            listVO.add(new MainNewVO(mainNew));

        return listVO;
    }
}
