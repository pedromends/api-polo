package com.ifmg.apipolo.entity;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;


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

    @Column(name = "is_expired")
    private Boolean expired;

    public Token(User user, Long validityInMinutes, String secretKey){
        this.user = user;
        this.token = Jwts.builder()
                .claim("id_usuario", user.getId())
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plus(validityInMinutes, ChronoUnit.MINUTES)))
                .signWith(SignatureAlgorithm.HS256, Base64.getEncoder()
                        .encodeToString(secretKey.getBytes(StandardCharsets.UTF_8)))
                .compact();
    }
}
