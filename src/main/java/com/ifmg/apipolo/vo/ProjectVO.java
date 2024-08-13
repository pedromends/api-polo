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
    private Researcher coordinator;
    private Company company;
    private String name;
    private String resume;
    private String situation;
    private String value;
    private String modalName;
    private String accordionId;
    private String headerName;
    private String headerBody;
    private Boolean active;

    public ProjectVO(Project project) {
        this.id = project.getId();
        this.modality = project.getModality();
        this.coordinator = project.getCoordinator();
        this.company = project.getCompany();
        this.name = project.getName();
        this.resume = project.getResume();
        this.situation = project.getSituation();
        this.value = project.getValue();
        this.modalName = project.getModalName();
        this.accordionId = project.getAccordionId();
        this.headerName = project.getHeaderName();
        this.headerBody = project.getHeaderBody();
        this.active = project.getActive();
    }
}
