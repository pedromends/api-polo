package com.ifmg.apipolo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "event", schema = "ifmg")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "month")
    private String month;

    @Column(name = "day")
    private String day;

    @Column(name = "title")
    private String title;

    @Column(name = "hour")
    private String hour;

    @Column(name = "local")
    private String local;
}
