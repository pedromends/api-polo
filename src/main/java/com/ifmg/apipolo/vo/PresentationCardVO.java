package com.ifmg.apipolo.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ifmg.apipolo.entity.PresentationCard;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PresentationCardVO {

    private Long id;
    private int num;
    private String text;

    public PresentationCardVO(PresentationCard presentationCard) {
        this.id = presentationCard.getId();
        this.num = presentationCard.getNum();
        this.text = presentationCard.getText();
    }
}
