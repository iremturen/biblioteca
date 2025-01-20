package com.biblioteca.repositories.interfaces;

public interface IRatingRepository {

    Double getAverageRating(Integer bookId);
    void saveRating(Integer bookId, Integer userId, Integer rating);
}
