package com.biblioteca.services;

import com.biblioteca.dto.ReviewMessage;
import com.biblioteca.models.Review;
import com.biblioteca.repositories.interfaces.IReviewRepository;
import com.biblioteca.services.interfaces.IReviewService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReviewService implements IReviewService {

    private IReviewRepository reviewRepository;

    @Override
    public void saveReview(ReviewMessage reviewMessage) {
        Review review = new Review();
        review.setBookId(reviewMessage.getBookId());
        review.setUserId(reviewMessage.getUserId());
        review.setReview(reviewMessage.getReview());
        review.setCreatedAt(new Date());
        review.setUpdatedAt(new Date());
        reviewRepository.save(review);
    }


    @Override
    public List<ReviewMessage> getReviews(Integer bookId) {
        return List.of();
    }
}
