package com.ifmg.apipolo.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ifmg.apipolo.entity.Company;
import com.ifmg.apipolo.entity.Modality;
import com.ifmg.apipolo.entity.Project;
import com.ifmg.apipolo.entity.Researcher;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProjectVO {

    private Long id;
    private Modality modality;
    private Researcher researcher;
    private Company company;
    private String name;
    private String resume;
    private String coordinator;
    private String situation;
    private String value;
    private String team;
    private String modalName;
    private String accordionId;
    private String headerName;
    private String headerBody;

    public ProjectVO(Project project) {
        this.id = project.getId();
        this.modality = project.getModality();
        this.researcher = project.getResearcher();
        this.company = project.getCompany();
        this.name = project.getName();
        this.resume = project.getResume();
        this.coordinator = project.getCoordinator();
        this.situation = project.getSituation();
        this.value = project.getValue();
        this.team = project.getTeam();
        this.modalName = project.getModalName();
        this.accordionId = project.getAccordionId();
        this.headerName = project.getHeaderName();
        this.headerBody = project.getHeaderBody();
    }
}
