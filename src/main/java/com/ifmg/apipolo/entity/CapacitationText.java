package com.ifmg.apipolo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "texto_capacitacao", schema = "ifmg-polo")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class CapacitationText {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "texto")
    private String text;
}
