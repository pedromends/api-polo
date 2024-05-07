package com.ifmg.apipolo.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ifmg.apipolo.entity.Modality;
import com.ifmg.apipolo.entity.Researcher;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProjectVO {

    private Long id;
    private Modality modality;
    private Researcher researcher;
    private String name;
    private String resume;
    private String coordinator;
    private String situation;
    private String value;
    private String team;
}
