package com.ifmg.apipolo.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ifmg.apipolo.entity.Image;
import com.ifmg.apipolo.entity.WhoWeAre;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class WhoWeAreVO {

    private Long id;
    private String title;
    private String parag;
    private Image image;

    public WhoWeAreVO(WhoWeAre whoWeAre) {
        this.id = whoWeAre.getId();
        this.title = whoWeAre.getTitle();
        this.parag = whoWeAre.getParag();
        this.image = whoWeAre.getImg();
    }
}
