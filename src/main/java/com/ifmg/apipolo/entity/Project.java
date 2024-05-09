package com.ifmg.apipolo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "projeto", schema = "ifmg-polo")
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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_empresa", referencedColumnName = "id")
    private Company company;

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

    @Column(name = "modal_name")
    private String modalName;

    @Column(name = "accordion_id")
    private String accordionId;

    @Column(name = "header_name")
    private String headerName;

    @Column(name = "header_body")
    private String headerBody;
}
