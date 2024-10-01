package com.ifmg.apipolo.entity;

import com.ifmg.apipolo.vo.ImageVO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "imagem", schema = "ifmg-polo")
@Getter
@Setter
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    private String name;

    @Column(name = "codigo")
    private String code;

    public Image(Image img) {
        this.id = img.getId();
        this.name = img.getName();
        this.code = img.getCode();
    }
}
