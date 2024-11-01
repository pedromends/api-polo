package com.ifmg.apipolo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "quem_somos", schema = "ifmg")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class WhoWeAre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_imagem", referencedColumnName = "id")
    private Image img;

    @Column(name = "titulo")
    private String title;

    @Column(name = "paragrafo")
    private String parag;
}
