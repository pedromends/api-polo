package com.ifmg.apipolo.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ifmg.apipolo.entity.Image;
import com.ifmg.apipolo.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserVO {

    private Long id;
    private Image img;
    private String firstName;
    private String role;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private String confirmPassword;
    private String locked;
    private String enabled;
    private String token;
    private String address;
    private String phone;
    private String aboutMe;
    private String education;
    private String profession;
    private String city;
    private String department;

    public UserVO(User user) {
        this.id = user.getId();
        this.img = user.getImg();
        this.firstName = user.getFirstName();
        this.role = user.getRole();
        this.lastName = user.getLastName();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.address = user.getAddress();
        this.phone = user.getPhone();
        this.aboutMe = user.getAboutMe();
        this.education = user.getEducation();
        this.profession = user.getProfession();
        this.city = user.getCity();
        this.department = user.getDepartment();
    }
}
