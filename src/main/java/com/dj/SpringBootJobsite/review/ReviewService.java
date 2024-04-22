package com.dj.SpringBootJobsite.review;

import java.util.List;

public interface ReviewService {
    public List<Review> getAllReviews(Long companyId);
    public boolean addReview(Long companyId, Review review);

    Review getReviewById(Long companyId, Long reviewId);

    boolean deleteReviewById(Long companyId, Long reviewId);
}
