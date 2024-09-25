package com.ifmg.apipolo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "card_talento", schema = "ifmg")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class TalentCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_imagem", referencedColumnName = "id")
    private Image img;

    @Column(name = "nome")
    private String name;

    @Column(name = "profissao")
    private String profession;

    @Column(name = "detalhes")
    private String details;

    @Column(name = "eh_ativo")
    private Boolean active;
}
