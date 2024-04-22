package com.ifmg.apipolo.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ifmg.apipolo.entity.NewsCard;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class NewsCardVO {

    private Long id;
    private String tip;
    private String title;
    private String date;
    private String read;
    private String image;
    private String linkTo;
    private String attr1;

    public NewsCardVO(NewsCard newsCard) {
        this.id = newsCard.getId();
        this.tip = newsCard.getTip();
        this.title = newsCard.getTitle();
        this.date = newsCard.getDate();
        this.read = newsCard.getRead();
        this.image = newsCard.getImage();
        this.linkTo = newsCard.getLinkTo();
        this.attr1 = newsCard.getAttr1();
    }
}
