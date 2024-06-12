package com.ifmg.apipolo.service;

import com.ifmg.apipolo.entity.Campus;
import com.ifmg.apipolo.entity.Image;
import com.ifmg.apipolo.entity.Researcher;
import com.ifmg.apipolo.repository.CampusRepository;
import com.ifmg.apipolo.repository.ImageRepository;
import com.ifmg.apipolo.repository.ResearcherRepository;
import com.ifmg.apipolo.vo.NewsCardVO;
import com.ifmg.apipolo.vo.ResearcherVO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        Image newImage = new Image();

        newImage.setCode(researcherVO.getImg().getCode());
        imageRepository.save(newImage);

        Image returnImage = imageRepository.getlastInserted();

        newResearcher.setImg(returnImage);
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

        Researcher researcher = researcherRepository.getReferenceById(researcherVO.getId());
        Campus campus = campusRepository.getReferenceById(researcherVO.getCampus().getId());

        researcher.setCampus(campus);
        researcher.setFirstName(researcherVO.getFirstName());
        researcher.setLastName(researcherVO.getLastName());
        researcher.setEmail(researcherVO.getEmail());
        researcher.setCourse(researcherVO.getCourse());
        researcher.setLevel(researcherVO.getLevel());
        researcher.setSex(researcherVO.getSex());

        imageRepository.updateCodeById(researcherVO.getImg().getId(),researcherVO.getImg().getCode());
        researcherRepository.save(researcher);
    }

    public List<ResearcherVO> list(){

        List<ResearcherVO> listVO = new ArrayList<>();
        List<Researcher> list = researcherRepository.findAll();

        for(Researcher researcher : list)
            listVO.add(new ResearcherVO(researcher));

        return listVO;
    }

    public ResearcherVO getByEmail(String email) {
        Researcher researcher = researcherRepository.getByEmail(email);
        return new ResearcherVO(researcher);
    }

    @Transactional
    public void deleteResearcher(Long id) {

        // fazer deleção virtual
        Optional<Researcher> researcher = researcherRepository.findById(id);
        researcher.get().setCampus(null);
        researcher.get().setImg(null);

        researcherRepository.save(researcher.get());
        researcherRepository.delete(researcher.get());
    }

}
