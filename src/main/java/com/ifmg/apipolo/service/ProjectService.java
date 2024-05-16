package com.ifmg.apipolo.service;

import com.ifmg.apipolo.entity.Project;
import com.ifmg.apipolo.repository.CompanyRepository;
import com.ifmg.apipolo.repository.ProjectRepository;
import com.ifmg.apipolo.repository.ResearcherRepository;
import com.ifmg.apipolo.vo.ProjectVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void createProject(ProjectVO projectVO) {

        Project project = new Project();

        project.setModality(projectVO.getModality());
        project.setCoordinator(projectVO.getCoordinator());
        project.setCompany(projectVO.getCompany());
        project.setModalName(projectVO.getModalName());
        project.setAccordionId(projectVO.getAccordionId());
        project.setHeaderName(projectVO.getHeaderName());
        project.setHeaderBody(projectVO.getHeaderBody());
        project.setName(projectVO.getName());
        project.setResume(projectVO.getResume());
        project.setSituation(projectVO.getSituation());
        project.setValue(projectVO.getValue());

        projectRepository.save(project);
    }

    public void updateProject(ProjectVO projectVO) {

        Project project = projectRepository.getReferenceById(projectVO.getId());
        System.out.println(projectVO);
        project.setModality(projectVO.getModality());
        project.setCoordinator(researcherRepository.getReferenceById(projectVO.getCoordinator().getId()));
        project.setCompany(companyRepository.getReferenceById(projectVO.getCompany().getId()));
        project.setName(projectVO.getName());
        project.setResume(projectVO.getResume());
        project.setSituation(projectVO.getSituation());
        project.setValue(projectVO.getValue());

        projectRepository.save(project);
    }

    public List<ProjectVO> list(){

        List<ProjectVO> listVO = new ArrayList<>();
        List<Project> list = projectRepository.findAll();

        for(Project project : list)
            listVO.add(new ProjectVO(project));

        return listVO;
    }

    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }
}
