package com.ifmg.apipolo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "researcher", schema = "ifmg")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Researcher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "position")
    private String position;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "lattes_link")
    private String lattesLink;

    @Column(name = "course")
    private String course;
}
