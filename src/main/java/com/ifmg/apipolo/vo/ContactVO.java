package com.ifmg.apipolo.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ifmg.apipolo.entity.Contact;
import com.ifmg.apipolo.entity.Image;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContactVO {

    private Long id;
    private Image img;
    private String name;
    private String position;
    private String email;
    private String phoneNumber;

    public ContactVO(Contact contact) {
        this.id = contact.getId();
        this.img = contact.getImg();
        this.name = contact.getName();
        this.position = contact.getPosition();
        this.email = contact.getEmail();
        this.phoneNumber = contact.getPhoneNumber();
    }
}
