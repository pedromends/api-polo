package com.ifmg.apipolo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Entity
@Table(name = "empresa_aluno", schema = "ifmg-polo")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class StudentTeam {

    @Id
    private int id;

    @OneToMany(mappedBy="id")
    private Set<Student> students;

    @OneToMany(mappedBy="id")
    private Set<Project> projects;
}
