package com.biblioteca.services.interfaces;

import org.springframework.validation.annotation.Validated;

@Validated
public interface IRatingService {
    Double getAverageRating(Integer bookId);
    void saveRating(Integer bookId, Integer userId, Integer rating);

}
