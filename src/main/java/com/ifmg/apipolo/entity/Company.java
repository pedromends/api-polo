package com.ifmg.apipolo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "empresa", schema = "ifmg-polo")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_imagem", referencedColumnName = "id")
    private Image img;

    @Column(name = "nome")
    private String name;

    @Column(name = "modal_name")
    private String modalName;

    @Column(name = "accordion_id")
    private String accordionId;

    @Column(name = "header_name")
    private String headerName;

    @Column(name = "header_body")
    private String headerBody;

    @Column(name = "classificacao")
    private String classification;

    @Column(name = "cnpj")
    private String cnpj;
}
