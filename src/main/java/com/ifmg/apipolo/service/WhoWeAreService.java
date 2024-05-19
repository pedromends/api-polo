package com.ifmg.apipolo.service;

import com.ifmg.apipolo.entity.WhoWeAre;
import com.ifmg.apipolo.repository.WhoWeAreRepository;
import com.ifmg.apipolo.vo.WhoWeAreVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WhoWeAreService {

    @Autowired
    WhoWeAreRepository whoWeAreRepository;

    public void updateAboutUs(WhoWeAreVO whoWeAreVO) {
        WhoWeAre whoWeAre = whoWeAreRepository.getReferenceById(whoWeAreVO.getId());
        whoWeAre.setTitle(whoWeAreVO.getTitle());
        whoWeAre.setParag(whoWeAreVO.getParag());
        whoWeAreRepository.save(whoWeAre);
    }

    public Optional<WhoWeAreVO> getText(){

        Optional<WhoWeAre> whoWeAre = whoWeAreRepository.findById(Long.valueOf(1));
        WhoWeAreVO whoWeAreVO = new WhoWeAreVO(whoWeAre.get());

        return Optional.of(whoWeAreVO);
    }
}
