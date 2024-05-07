package com.ifmg.apipolo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "card_evento", schema = "ifmg-polo")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class EventCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "mes")
    private String month;

    @Column(name = "dia")
    private String day;

    @Column(name = "titulo")
    private String title;

    @Column(name = "hora")
    private String hour;

    @Column(name = "local")
    private String local;
}
