package com.ifmg.apipolo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "advantages", schema = "ifmg")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Advantages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "differential")
    private String differential;

    @Column(name = "desc")
    private String description;

    @Column(name = "img")
    private String img;
}
