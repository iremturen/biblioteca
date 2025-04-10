package com.biblioteca.repositories.interfaces;

import com.biblioteca.dto.RatingMessage;
import com.biblioteca.models.RatingAverage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRatingRepository extends JpaRepository<RatingAverage, Integer> {
    Optional<RatingAverage> findByBookId(Integer bookId);

}
