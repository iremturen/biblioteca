package com.biblioteca.services.interfaces;

import com.biblioteca.dto.RatingMessage;
import com.biblioteca.models.RatingAverage;
import org.springframework.validation.annotation.Validated;

@Validated
public interface IRatingService {
    void saveOrUpdate(Double average, Integer bookId);
    RatingAverage getAverageRating(Integer bookId);
}
