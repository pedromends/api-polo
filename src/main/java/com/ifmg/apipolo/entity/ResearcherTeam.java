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
@Table(name = "equipe_pesquisador", schema = "ifmg-polo")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class ResearcherTeam {

    @Id
    private int id;

    @OneToMany(mappedBy="pesquisador")
    private Set<Researcher> researchers;

    @OneToMany(mappedBy="projeto")
    private Set<Project> projects;
}
