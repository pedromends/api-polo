package com.ifmg.apipolo.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ifmg.apipolo.entity.PresenterCard;
import jakarta.persistence.Column;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PresenterCardVO {

    private Long id;
    private String num;
    private String text;

    public PresenterCardVO(PresenterCard presenterCard) {
        this.id = presenterCard.getId();
        this.num = presenterCard.getNum();
        this.text = presenterCard.getText();
    }
}
