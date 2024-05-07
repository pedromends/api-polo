package com.ifmg.apipolo.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ifmg.apipolo.entity.CapacitationCard;
import com.ifmg.apipolo.entity.Image;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CapacitationCardVO {
    private Long id;
    private Image img;
    private String title;
    private String subtitle;

    public CapacitationCardVO(CapacitationCard capacitationCard) {
        this.img = capacitationCard.getImg();
        this.id = capacitationCard.getId();
        this.title = capacitationCard.getTitle();
        this.subtitle = capacitationCard.getSubtitle();
    }
}
