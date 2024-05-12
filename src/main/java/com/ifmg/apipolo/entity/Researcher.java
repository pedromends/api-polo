package com.ifmg.apipolo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "pesquisador", schema = "ifmg-polo")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Researcher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_imagem", referencedColumnName = "id")
    private Image img;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_campus", referencedColumnName = "id")
    private Campus campus;

    @Column(name = "nome")
    private String firstName;

    @Column(name = "sobrenome")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "curso")
    private String course;

    @Column(name = "titulacao")
    private String level;

    @Column(name = "sexo")
    private String sex;
}
