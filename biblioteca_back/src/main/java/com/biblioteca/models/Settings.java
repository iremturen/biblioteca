package com.biblioteca.models;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Settings implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String info_type;
    private String title;
    private String content;
}
