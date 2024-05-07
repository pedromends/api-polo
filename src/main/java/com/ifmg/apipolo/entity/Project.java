package com.ifmg.apipolo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "project", schema = "ifmg-polo")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_modalidade", referencedColumnName = "id")
    private Modality modality;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_coordenador", referencedColumnName = "id")
    private Researcher researcher;

    @Column(name = "nome")
    private String name;

    @Column(name = "resumo")
    private String resume;

    @Column(name = "coordenador")
    private String coordinator;

    @Column(name = "situacao")
    private String situation;

    @Column(name = "valor")
    private String value;

    @Column(name = "equipe")
    private String team;
}
