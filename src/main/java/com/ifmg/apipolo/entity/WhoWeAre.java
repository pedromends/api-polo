package com.ifmg.apipolo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "who_we_are", schema = "ifmg")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class WhoWeAre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "parag")
    private String parag;
}
