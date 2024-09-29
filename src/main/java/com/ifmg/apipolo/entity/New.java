package com.ifmg.apipolo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "noticia", schema = "ifmg-polo")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class New {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "data")
    private String date;

    @Column(name = "titulo")
    private String title;

    @Column(name = "codigo")
    private String code;

    @Column(name = "eh_ativo")
    private Boolean active;

    @Column(name = "principal")
    private Boolean isMain;
}
