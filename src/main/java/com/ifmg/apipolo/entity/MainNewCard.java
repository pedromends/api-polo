package com.ifmg.apipolo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "main_new", schema = "ifmg-polo")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class MainNewCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "tip")
    private String tip;

    @Column(name = "title")
    private String title;

    @Column(name = "parag")
    private String parag;

    @Column(name = "image")
    private String image;

    @Column(name = "link_to")
    private String linkTo;

    @Column(name = "attr_1")
    private String attr1;
}
