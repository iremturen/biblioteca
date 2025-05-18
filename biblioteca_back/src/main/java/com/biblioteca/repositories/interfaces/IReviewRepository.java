package com.biblioteca.repositories.interfaces;

import com.biblioteca.models.RatingAverage;
import com.biblioteca.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IReviewRepository extends JpaRepository<Review, Integer> {
    Optional<Review> findByBookId(Integer bookId);

}
