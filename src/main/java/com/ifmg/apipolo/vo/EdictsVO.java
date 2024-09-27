package com.ifmg.apipolo.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ifmg.apipolo.entity.Edicts;
import jakarta.persistence.Column;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class EdictsVO {

    private Long id;
    private String title;
    private String link;
    private String filename;

    public EdictsVO(Edicts edicts) {
        this.id = edicts.getId();
        this.title = edicts.getTitle();
        this.link = edicts.getLink();
        this.filename = edicts.getFilename();
    }
}
