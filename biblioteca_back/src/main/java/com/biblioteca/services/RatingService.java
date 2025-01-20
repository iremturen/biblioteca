package com.biblioteca.services;

import com.biblioteca.repositories.interfaces.IRatingRepository;
import com.biblioteca.services.interfaces.IRatingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
@AllArgsConstructor
public class RatingService implements IRatingService {

    private IRatingRepository ratingRepository;

    @Override
    public Double getAverageRating(Integer bookId) {
        return ratingRepository.getAverageRating(bookId);
    }

    @Override
    public void saveRating(Integer bookId, Integer userId, Integer rating) {
        ratingRepository.saveRating(bookId, userId, rating);
    }
}
