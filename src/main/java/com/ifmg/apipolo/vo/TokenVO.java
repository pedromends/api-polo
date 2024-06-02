package com.ifmg.apipolo.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ifmg.apipolo.entity.Token;
import com.ifmg.apipolo.entity.User;
import jakarta.persistence.Column;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TokenVO {

    private Long id;
    private String token_code;
    private String tokenType;
    private String confirmedAt;
    private String expiresAt;
    private String expired;
    private User user;

    public TokenVO(Token token) {
        this.id = token.getId();
        this.token_code = token.getToken();
        this.user = token.getUser();
    }
}
