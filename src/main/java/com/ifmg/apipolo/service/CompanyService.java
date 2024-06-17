package com.ifmg.apipolo.service;

import com.ifmg.apipolo.entity.Company;
import com.ifmg.apipolo.entity.Image;
import com.ifmg.apipolo.entity.Project;
import com.ifmg.apipolo.repository.CompanyRepository;
import com.ifmg.apipolo.repository.ImageRepository;
import com.ifmg.apipolo.vo.CompanyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private ImageRepository imageRepository;

    public void createCompany(CompanyVO companyVO) {
        Company company = new Company();
        Image newImage = new Image();

        newImage.setCode(companyVO.getImage().getCode());
        imageRepository.save(newImage);

        company.setImg(imageRepository.getlastInserted());
        company.setName(companyVO.getName());
        company.setClassification(companyVO.getClassification());
        company.setCnpj(companyVO.getCnpj());

        companyRepository.save(company);
    }

    public List<CompanyVO> list(){

        List<CompanyVO> listVO = new ArrayList<>();
        List<Company> list = companyRepository.findActives();

        for(Company company : list)
            listVO.add(new CompanyVO(company));

        return listVO;
    }

    @Transactional
    public void updateCompany(CompanyVO companyVO) {

        Company company = companyRepository.getReferenceById(companyVO.getId());

        if(companyVO.getImage().getId() != null) {
            Image cardImage = imageRepository.getReferenceById(companyVO.getImage().getId());
            cardImage.setCode(companyVO.getImage().getCode());

            if(companyVO.getImage() != null)
                company.setImg(cardImage);

            imageRepository.save(cardImage);
        }

        if(companyVO.getName() != null)
            company.setName(companyVO.getName());

        if(companyVO.getClassification() != null)
            company.setClassification(companyVO.getClassification());

        if(companyVO.getCnpj() != null)
            company.setCnpj(companyVO.getCnpj());

        companyRepository.save(company);
    }

    @Transactional
    public void deleteCompany(Long id) {
        Company company = companyRepository.getReferenceById(id);

        company.setActive(false);

        companyRepository.save(company);
    }
}
