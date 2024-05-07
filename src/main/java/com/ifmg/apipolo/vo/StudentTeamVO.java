package com.ifmg.apipolo.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ifmg.apipolo.entity.Campus;
import com.ifmg.apipolo.entity.Image;
import com.ifmg.apipolo.entity.Student;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudentTeamVO {

    private Long id;
    private Image img;
    private Campus campus;
    private String firstName;
    private String lastName;
    private String level;
    private String sex;

    public StudentTeamVO(Student student) {
        this.id = student.getId();
        this.img = student.getImg();
        this.campus = student.getCampus();
        this.firstName = student.getFirstName();
        this.lastName = student.getLastName();
        this.level = student.getLevel();
        this.sex = student.getSex();
    }
}
