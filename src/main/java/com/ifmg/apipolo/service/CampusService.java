package com.ifmg.apipolo.service;

import com.ifmg.apipolo.entity.Campus;
import com.ifmg.apipolo.repository.CampusRepository;
import com.ifmg.apipolo.vo.CampusVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CampusService {

    @Autowired
    private CampusRepository campusRepository;

    public List<CampusVO> list() {
        List<CampusVO> listVO = new ArrayList<>();
        List<Campus> list = campusRepository.findAll();
        for(Campus campus : list) {
            listVO.add(new CampusVO(campus));
        }
        return listVO;
    }
}
