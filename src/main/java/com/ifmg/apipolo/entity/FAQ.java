package com.ifmg.apipolo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "faq", schema = "ifmg-polo")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class FAQ {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "pergunta")
    private String question;

    @Column(name = "resposta")
    private String answer;

    @Column(name = "idheading")
    private String idHeading;

    @Column(name = "idbody")
    private String idBody;
}
