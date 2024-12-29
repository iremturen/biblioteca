package com.biblioteca.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterRequest {

    private String username;
    private String password;
    private String name;
    private String surname;
    private String email;
    private String telNo;
    private String country;
    private String city;
    private Date birthDate;
}
