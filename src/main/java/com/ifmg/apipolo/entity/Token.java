package com.ifmg.apipolo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name = "token", schema = "ifmg-polo")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    private User user;

    @Column(name = "token_code")
    private String token;

    @Column(name = "token_type")
    private String tokenType;

    @Column(name = "confirmed_at")
    private String confirmedAt;

    @Column(name = "expires_at")
    private String expiresAt;

    @Column(name = "expired")
    private String expired;

    public Token(User user, String token){
        this.user = user;
        this.token = token;
    }
}
