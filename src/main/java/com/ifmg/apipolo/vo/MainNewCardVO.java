package com.ifmg.apipolo.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ifmg.apipolo.entity.Image;
import com.ifmg.apipolo.entity.MainNewCard;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class MainNewCardVO {

    private Long id;
    private Image image;
    private String tip;
    private String title;
    private String paragraph;

    public MainNewCardVO(MainNewCard mainNewCard) {
        this.id = mainNewCard.getId();
        this.tip = mainNewCard.getTip();
        this.title = mainNewCard.getTitle();
        this.paragraph = mainNewCard.getParagraph();
    }
}
