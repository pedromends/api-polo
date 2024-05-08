package com.ifmg.apipolo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "card_apresentacao", schema = "ifmg")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class PresentationCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "numero")
    private String num;

    @Column(name = "frase")
    private String text;
}
