package com.ifmg.apipolo.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ifmg.apipolo.entity.CapacitationText;
import jakarta.persistence.Column;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CapacitationTextVO {

    private Long id;
    private String text;

    public CapacitationTextVO(CapacitationText capacitationText) {
        this.id = capacitationText.getId();
        this.text = capacitationText.getText();
    }
}
