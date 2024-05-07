package com.ifmg.apipolo.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ifmg.apipolo.entity.Modality;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ModalityVO {
    private Long id;
    private String name;
    private String mincompany;
    private String embrapii_max;

    public ModalityVO(Modality modality) {
        this.id = modality.getId();
        this.name = modality.getName();
        this.mincompany = modality.getMincompany();
        this.embrapii_max = modality.getEmbrapii_max();
    }
}
