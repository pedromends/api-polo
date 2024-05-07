package com.ifmg.apipolo.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ifmg.apipolo.entity.Researcher;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResearcherVO {

    private Long id;
    private String position;
    private String name;
    private String email;
    private String lattesLink;
    private String course;

    public ResearcherVO(Researcher researcher) {
        this.id = researcher.getId();
        this.position = researcher.getPosition();
        this.name = researcher.getFirstName();
        this.email = researcher.getLastName();
        this.lattesLink = researcher.getLattesLink();
        this.course = researcher.getCourse();
    }
}
