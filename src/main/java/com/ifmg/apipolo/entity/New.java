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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_img1", referencedColumnName = "id")
    private Image img1;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_img2", referencedColumnName = "id")
    private Image img2;

    @Column(name = "data")
    private String date;

    @Column(name = "titulo")
    private String title;

    @Column(name = "paragrafo1")
    private String paragraph1;

    @Column(name = "paragrafo2")
    private String paragraph2;
}
