package com.ifmg.apipolo.vo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ifmg.apipolo.entity.Image;
import com.ifmg.apipolo.entity.New;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class NewVO {

    private Long id;
    private Image img1;
    private Image img2;
    private String title;
    private String date;
    private String paragraph1;
    private String paragraph2;
    private String tip;
    private Boolean isMain;

    public NewVO(New newNew) {
        this.id = newNew.getId();
        this.img1 = newNew.getImg1();
        this.img2 = newNew.getImg2();
        this.title = newNew.getTitle();
        this.date = newNew.getDate();
        this.paragraph1 = newNew.getParagraph1();
        this.isMain = null;
        this.tip = null;
        this.paragraph2 = newNew.getParagraph2();
    }
}

