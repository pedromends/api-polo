package com.ifmg.apipolo.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ifmg.apipolo.entity.MainNewCard;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class MainNewVO {

    private Long id;
    private String tip;
    private String title;
    private String parag;
    private String image;
    private String linkTo;
    private String attr1;

    public MainNewVO(MainNewCard mainNewCard) {
        this.id = mainNewCard.getId();
        this.tip = mainNewCard.getTip();
        this.title = mainNewCard.getTitle();
        this.parag = mainNewCard.getParag();
        this.image = mainNewCard.getImage();
        this.linkTo = mainNewCard.getLinkTo();
        this.attr1 = mainNewCard.getAttr1();
    }
}
