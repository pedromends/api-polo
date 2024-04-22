package com.ifmg.apipolo.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ifmg.apipolo.entity.Capacitation;
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

    public CapacitationVO(Capacitation capacitation) {
        this.id = capacitation.getId();
        this.title = capacitation.getTitle();
        this.subtitle = capacitation.getSubtitle();
        this.img = capacitation.getImg();
        this.linkTo = capacitation.getLinkTo();
    }
}
