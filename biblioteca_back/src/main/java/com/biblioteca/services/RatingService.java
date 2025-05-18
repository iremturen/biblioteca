package com.biblioteca.services;

import com.biblioteca.models.RatingAverage;
import com.biblioteca.repositories.interfaces.IRatingRepository;
import com.biblioteca.services.interfaces.IRatingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Date;

@Service
@Validated
@AllArgsConstructor
public class RatingService implements IRatingService {

    private IRatingRepository ratingRepository;

    @Override
    public void saveAverage(Double average, Integer bookId) {
        RatingAverage existingRating = ratingRepository.findByBookId(bookId).orElse(null);
        if (existingRating != null) {
            existingRating.setAverageRating(average);
            existingRating.setUpdatedAt(new Date());
            ratingRepository.save(existingRating);
        } else {
            RatingAverage newRating = new RatingAverage(bookId, average, new Date());
            ratingRepository.save(newRating);
        }
    }

    @Override
    public RatingAverage getAverageRating(Integer bookId) {
        return ratingRepository.findByBookId(bookId)
                .orElse(new RatingAverage(bookId, 0.0, new Date()));
    }
}
