package com.biblioteca.dto;

import com.biblioteca.models.Review;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ReviewMessage  implements Serializable {
    private static final long serialVersionUID = 1L;

    private String actionType;
    private Review rating;
}
