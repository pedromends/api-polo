package com.ifmg.apipolo.vo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ifmg.apipolo.entity.Image;
import com.ifmg.apipolo.entity.New;
import jakarta.persistence.Column;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class NewVO {

    private Long id;
    private String date;
    private String title;
    private String code;
    private Boolean active;
    private Boolean isMain;

    public NewVO(New newNew) {
        this.id = newNew.getId();
        this.title = newNew.getTitle();
        this.code = newNew.getCode();
        this.date = newNew.getDate();
        this.isMain = null;
        this.active = newNew.getActive();
    }
}

