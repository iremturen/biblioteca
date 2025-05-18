package com.biblioteca.services.interfaces;

import com.biblioteca.dto.ReviewMessage;

import java.util.List;

public interface IReviewService {
    List<ReviewMessage> getReviews(Integer bookId);
    void saveReview(ReviewMessage reviewMessage);

}
