package com.ifmg.apipolo.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ifmg.apipolo.entity.Campus;
import com.ifmg.apipolo.entity.Image;
import com.ifmg.apipolo.entity.Researcher;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResearcherVO {

    private Long id;
    private Image img;
    private Campus campus;
    private String firstName;
    private String lastName;
    private String email;
    private String course;
    private String level;
    private String sex;

    public ResearcherVO(Researcher researcher) {
        this.id = researcher.getId();
        this.img = researcher.getImg();
        this.campus = researcher.getCampus();
        this.firstName = researcher.getFirstName();
        this.lastName = researcher.getLastName();
        this.email = researcher.getEmail();
        this.course = researcher.getCourse();
        this.level = researcher.getLevel();
        this.sex = researcher.getSex();
    }
}
