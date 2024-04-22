package com.ifmg.apipolo.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ifmg.apipolo.entity.MainNew;
import jakarta.persistence.Column;

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

    public MainNewVO(MainNew mainNew) {
        this.id = mainNew.getId();
        this.tip = mainNew.getTip();
        this.title = mainNew.getTitle();
        this.parag = mainNew.getParag();
        this.image = mainNew.getImage();
        this.linkTo = mainNew.getLinkTo();
        this.attr1 = mainNew.getAttr1();
    }
}
