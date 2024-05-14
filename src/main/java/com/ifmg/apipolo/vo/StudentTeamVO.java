package com.ifmg.apipolo.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ifmg.apipolo.entity.*;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudentTeamVO {

    private Set<Student> students;
    private Set<Project> projects;

    public StudentTeamVO(StudentTeam studentTeam) {
        this.students = studentTeam.getStudents();
        this.projects = studentTeam.getProjects();
    }
}
