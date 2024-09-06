package com.biblioteca.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer bookId;
    private String book_name;
    private String author;
    private Integer book_page;
    private Integer visibility;
    private Date addition_at;
    private String publishing_house;
    private String publishing_year;
    private String language;
    private String image;

}
