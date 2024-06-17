package com.ifmg.apipolo.service;

import com.ifmg.apipolo.entity.Project;
import com.ifmg.apipolo.repository.CompanyRepository;
import com.ifmg.apipolo.repository.ModalityRepository;
import com.ifmg.apipolo.repository.ProjectRepository;
import com.ifmg.apipolo.repository.ResearcherRepository;
import com.ifmg.apipolo.vo.ProjectVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ResearcherRepository researcherRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private ModalityRepository modalityRepository;

    public void createProject(ProjectVO projectVO) {

        Project project = new Project();
        var modality = modalityRepository.findById(projectVO.getModality().getId());
        var researcher = researcherRepository.findById(projectVO.getCoordinator().getId());
        var company = companyRepository.findById(projectVO.getCompany().getId());
        String aux = projectVO.getName().toLowerCase().replace(" ","");

        project.setModality(modality.get());
        project.setCoordinator(researcher.get());
        project.setCompany(company.get());
        project.setModalName("modal_" + aux);
        project.setAccordionId("accordion_" + aux);
        project.setHeaderName("header_" + aux);
        project.setHeaderBody("heading_" + aux);
        project.setName(projectVO.getName());
        project.setResume(projectVO.getResume());
        project.setSituation(projectVO.getSituation());
        project.setValue(projectVO.getValue());

        projectRepository.save(project);
    }

    @Transactional(propagation=Propagation.REQUIRES_NEW)
    public void updateProject(ProjectVO projectVO) {

        Project project = projectRepository.getReferenceById(projectVO.getId());

        if(projectVO.getModality().getId() != null)
            project.setModality(modalityRepository.getReferenceById(projectVO.getModality().getId()));

        if(projectVO.getCoordinator().getId() != null)
            project.setCoordinator(researcherRepository.getReferenceById(projectVO.getCoordinator().getId()));

        if(projectVO.getCompany().getId() != null)
            project.setCompany(companyRepository.getReferenceById(projectVO.getCompany().getId()));

        if(projectVO.getName() != null)
            project.setName(projectVO.getName());

        if(projectVO.getResume() != null)
            project.setResume(projectVO.getResume());

        if(projectVO.getSituation() != null)
            project.setSituation(projectVO.getSituation());

        if(projectVO.getValue() != null)
            project.setValue(projectVO.getValue());

        projectRepository.save(project);
    }

    public List<ProjectVO> list(){

        List<ProjectVO> listVO = new ArrayList<>();
        List<Project> list = projectRepository.findActives();

        for(Project project : list)
            listVO.add(new ProjectVO(project));

        return listVO;
    }

    @Transactional
    public void deleteProject(Long id) { // não é possível fazer deleção física, apenas virtual
        Project project = projectRepository.getReferenceById(id);

        project.setActive(false);

        projectRepository.save(project);
    }
}
