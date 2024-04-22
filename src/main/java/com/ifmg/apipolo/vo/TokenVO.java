package com.ifmg.apipolo.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ifmg.apipolo.entity.Token;
import jakarta.persistence.Column;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TokenVO {

    private Long id;
    private String token;
    private String tokenType;
    private String confirmedAt;
    private String expiresAt;
    private String course;
    private String user;

    public TokenVO(Token token) {
        this.id = token.getId();
        this.token = token.getToken();
        this.tokenType = token.getTokenType();
        this.confirmedAt = token.getConfirmedAt();
        this.expiresAt = token.getExpiresAt();
        this.course = token.getCourse();
        this.user = token.getUser();
    }
}
