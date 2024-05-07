package com.ifmg.apipolo.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ifmg.apipolo.entity.Project;
import com.ifmg.apipolo.entity.Researcher;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResearcherTeamVO {

    private Set<Researcher> researchers;
    private Set<Project> projects;
}
