package com.ifmg.apipolo.record;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class EmailMessage {

    private String email;
    private String firstName;
    private String lastName;
    private String token;
}