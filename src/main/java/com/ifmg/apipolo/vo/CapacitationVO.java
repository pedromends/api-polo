package com.ifmg.apipolo.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ifmg.apipolo.entity.CapacitationCard;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CapacitationVO {
    private Long id;
    private String title;
    private String subtitle;
    private String img;
    private String linkTo;

    public CapacitationVO(CapacitationCard capacitationCard) {
        this.id = capacitationCard.getId();
        this.title = capacitationCard.getTitle();
        this.subtitle = capacitationCard.getSubtitle();
        this.img = capacitationCard.getImg();
        this.linkTo = capacitationCard.getLinkTo();
    }
}
