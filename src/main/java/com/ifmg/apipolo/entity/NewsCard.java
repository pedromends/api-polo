package com.ifmg.apipolo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "card_noticias", schema = "ifmg-polo")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class NewsCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_imagem", referencedColumnName = "id")
    private Image img;

    @Column(name = "dica")
    private String tip;

    @Column(name = "titulo")
    private String title;

    @Column(name = "data")
    private String date;

    @Column(name = "tempo_leitura")
    private String read;
}
