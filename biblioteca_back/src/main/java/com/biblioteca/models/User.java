package com.biblioteca.models;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer userId;
    private String username;
    private String name;
    private String surname;
    private String email;
    private String tel_no;
    private String country;
    private String city;
    private Date birth_date;
    private String profile_image;



}
