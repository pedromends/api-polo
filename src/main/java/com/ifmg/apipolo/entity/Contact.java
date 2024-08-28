package com.ifmg.apipolo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "contato", schema = "ifmg-polo")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "id_imagem", referencedColumnName = "id")
//    private Image img;

    @Column(name = "nome")
    private String name;

    @Column(name = "cargo")
    private String position;

    @Column(name = "celular")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "empresaExterna")
    private String externalCompany;

    @Column(name = "mensagem")
    private String message;

    @Column(name = "area")
    private String area;

    @Column(name = "lido")
    private boolean readed;
}
