package com.ifmg.apipolo.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ifmg.apipolo.entity.Image;
import com.ifmg.apipolo.entity.NewsCard;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class NewsCardVO {

    private Long id;
    private Image img;
    private String tip;
    private String title;
    private String date;
    private String read;

    public NewsCardVO(NewsCard newsCard) {
        this.id = newsCard.getId();
        this.img = newsCard.getImg();
        this.tip = newsCard.getTip();
        this.title = newsCard.getTitle();
        this.date = newsCard.getDate();
        this.read = newsCard.getRead();
    }
}
