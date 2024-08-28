package com.ifmg.apipolo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "usuario", schema = "ifmg-polo")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_imagem", referencedColumnName = "id")
    private Image img;

    @Column(name = "nome")
    private String firstName;

    @Column(name = "role")
    private String role;

    @Column(name = "sobrenome")
    private String lastName;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "eh_travado")
    private Boolean locked;

    @Column(name = "eh_ativo")
    private Boolean enabled;

    @Column(name = "endereco")
    private String address;

    @Column(name = "telefone")
    private String phone;

    @Column(name = "sobre")
    private String aboutMe;

    @Column(name = "educacao")
    private String education;

    @Column(name = "profissao")
    private String profession;

    @Column(name = "cidade")
    private String city;

    @Column(name = "departamento")
    private String department;
}
