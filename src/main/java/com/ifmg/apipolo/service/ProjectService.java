package com.ifmg.apipolo.service;

import com.ifmg.apipolo.entity.Project;
import com.ifmg.apipolo.repository.ProjectRepository;
import com.ifmg.apipolo.vo.ProjectVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public List<ProjectVO> list(){

        List<ProjectVO> listVO = new ArrayList<>();
        List<Project> list = projectRepository.findAll();

        for(Project project : list)
            listVO.add(new ProjectVO(project));

        return listVO;
    }
}
