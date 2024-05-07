package com.ifmg.apipolo.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ifmg.apipolo.entity.AdvantagesCard;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdvantagesVO {
    private Long id;
    private String differential;
    private String description;
    private String img;

    public AdvantagesVO(AdvantagesCard adv) {
        this.id = adv.getId();
        this.differential = adv.getDifferential();
        this.description = adv.getDescription();
        this.img = adv.getImg();
    }
}
