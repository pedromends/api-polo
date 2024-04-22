package com.ifmg.apipolo.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ifmg.apipolo.entity.Talent;
import jakarta.persistence.Column;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TalentVO {

    private Long id;
    private String image;
    private String name;
    private String profession;
    private String details;
    private String linkTo;

    public TalentVO(Talent talent) {
        this.id = talent.getId();
        this.image = talent.getImage();
        this.name = talent.getName();
        this.profession = talent.getProfession();
        this.details = talent.getDetails();
        this.linkTo = talent.getLinkTo();
    }
}
