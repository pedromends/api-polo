package com.ifmg.apipolo.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import com.ifmg.apipolo.entity.Image;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImageVO {
    private Long id;
    private String name;
    private String code;
    private String attr1;

    public ImageVO(Image image) {
        this.id = image.getId();
        this.name = image.getName();
        this.code = image.getCode();
        this.attr1 = image.getAttr1();
    }
}
