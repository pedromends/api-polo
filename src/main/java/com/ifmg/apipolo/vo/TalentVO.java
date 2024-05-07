package com.ifmg.apipolo.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ifmg.apipolo.entity.TalentCard;
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

    public TalentVO(TalentCard talentCard) {
        this.id = talentCard.getId();
        this.image = talentCard.getImage();
        this.name = talentCard.getName();
        this.profession = talentCard.getProfession();
        this.details = talentCard.getDetails();
        this.linkTo = talentCard.getLinkTo();
    }
}
