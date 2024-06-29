package com.ifmg.apipolo.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ifmg.apipolo.entity.Docs;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DocsVO {

    private Long id;
    private String title;
    private String link;

    public DocsVO(Docs docs) {
        this.id = docs.getId();
        this.title = docs.getTitle();
        this.link = docs.getLink();
    }
}
