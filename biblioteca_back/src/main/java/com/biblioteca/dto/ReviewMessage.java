package com.biblioteca.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class ReviewMessage  implements Serializable {
    private static final long serialVersionUID = 1L;

    private String actionType;
    private Integer bookId;
    private Integer userId;
    private String review;
    private Date createdAt;
    private Date updatedAt;}
