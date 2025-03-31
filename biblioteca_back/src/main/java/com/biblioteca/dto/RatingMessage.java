package com.biblioteca.dto;

import com.biblioteca.models.Rating;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class RatingMessage  implements Serializable {
    private static final long serialVersionUID = 1L;

    private String actionType;
    private Rating rating;
}
