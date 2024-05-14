package com.ifmg.apipolo.service;

import com.ifmg.apipolo.entity.Company;
import com.ifmg.apipolo.repository.CompanyRepository;
import com.ifmg.apipolo.vo.CompanyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public void createCompany(CompanyVO companyVO) {
        Company company = new Company();

        company.setName(companyVO.getName());
        company.setClassification(companyVO.getClassification());
        company.setCnpj(companyVO.getCnpj());
        company.setImg(companyVO.getImage());

        companyRepository.save(company);
    }

    public List<CompanyVO> list(){

        List<CompanyVO> listVO = new ArrayList<>();
        List<Company> list = companyRepository.findAll();

        for(Company company : list)
            listVO.add(new CompanyVO(company));

        return listVO;
    }

    public void updateCompany(CompanyVO companyVO) {
        Company company = companyRepository.getReferenceById(companyVO.getId());

        company.setName(companyVO.getName());
        company.setClassification(companyVO.getClassification());
        company.setCnpj(companyVO.getCnpj());
        company.setImg(companyVO.getImage());

        companyRepository.save(company);
    }

    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);
    }
}
