package com.ifmg.apipolo.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ifmg.apipolo.entity.Image;
import com.ifmg.apipolo.entity.TalentCard;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TalentCardVO {

    private Long id;
    private Image img;
    private String name;
    private String profession;
    private String details;

    public TalentCardVO(TalentCard talentCard) {
        this.id = talentCard.getId();
        this.img = talentCard.getImg();
        this.name = talentCard.getName();
        this.profession = talentCard.getProfession();
        this.details = talentCard.getDetails();
    }
}
