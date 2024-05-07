package com.ifmg.apipolo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Entity
@Table(name = "empresa_projeto", schema = "ifmg-polo")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class CompanyProject {

    @Id
    private int id;

    @OneToMany(mappedBy="empresa")
    private Set<Company> companies;

    @OneToMany(mappedBy="projeto")
    private Set<Project> projects;
}
