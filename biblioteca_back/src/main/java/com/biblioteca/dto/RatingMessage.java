package com.biblioteca.dto;

import com.biblioteca.models.Rating;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RatingMesaage {
    private String actionType;
    private Rating rating;
}
