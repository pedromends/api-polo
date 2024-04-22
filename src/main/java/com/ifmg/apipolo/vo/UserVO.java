package com.ifmg.apipolo.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ifmg.apipolo.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserVO {

    private Long id;
    private String firstName;
    private String role;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private String locked;
    private String enabled;
    private String image;

    public UserVO(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.role = user.getRole();
        this.lastName = user.getLastName();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.locked = user.getLocked();
        this.enabled = user.getEnabled();
        this.image = user.getImage();
    }
}
