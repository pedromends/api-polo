package com.ifmg.apipolo.service;

import com.ifmg.apipolo.entity.Campus;
import com.ifmg.apipolo.entity.Image;
import com.ifmg.apipolo.entity.Researcher;
import com.ifmg.apipolo.repository.CampusRepository;
import com.ifmg.apipolo.repository.ImageRepository;
import com.ifmg.apipolo.repository.ResearcherRepository;
import com.ifmg.apipolo.vo.NewsCardVO;
import com.ifmg.apipolo.vo.ResearcherVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResearcherService {

    @Autowired
    private ResearcherRepository researcherRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private CampusRepository campusRepository;

    public void createResearcher(ResearcherVO researcherVO) {

        Researcher newResearcher = new Researcher();

        newResearcher.setImg(researcherVO.getImg());
        newResearcher.setCampus(researcherVO.getCampus());
        newResearcher.setFirstName(researcherVO.getFirstName());
        newResearcher.setLastName(researcherVO.getLastName());
        newResearcher.setEmail(researcherVO.getEmail());
        newResearcher.setCourse(researcherVO.getCourse());
        newResearcher.setLevel(researcherVO.getLevel());
        newResearcher.setSex(researcherVO.getSex());

        researcherRepository.save(newResearcher);
    }

    public void updateResearcher(ResearcherVO researcherVO) {

        Image cardImage = imageRepository.getReferenceById(researcherVO.getImg().getId());
        Researcher researcher = researcherRepository.getReferenceById(researcherVO.getId());
        Campus campus = campusRepository.getReferenceById(researcherVO.getCampus().getId());

        researcher.setCampus(campus);
        researcher.setFirstName(researcherVO.getFirstName());
        researcher.setLastName(researcherVO.getLastName());
        researcher.setEmail(researcherVO.getEmail());
        researcher.setCourse(researcherVO.getCourse());
        researcher.setLevel(researcherVO.getLevel());
        researcher.setSex(researcherVO.getSex());

        cardImage.setCode(researcherVO.getImg().getCode());

        imageRepository.save(cardImage);
        researcherRepository.save(researcher);
    }

    public List<ResearcherVO> list(){

        List<ResearcherVO> listVO = new ArrayList<>();
        List<Researcher> list = researcherRepository.findAll();

        for(Researcher researcher : list)
            listVO.add(new ResearcherVO(researcher));

        return listVO;
    }

    public void deleteResearcher(Long id) {
        researcherRepository.deleteById(id);
    }
}
