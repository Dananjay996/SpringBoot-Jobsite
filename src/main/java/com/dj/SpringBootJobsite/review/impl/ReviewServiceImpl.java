package com.dj.SpringBootJobsite.review.impl;

import com.dj.SpringBootJobsite.company.Company;
import com.dj.SpringBootJobsite.company.CompanyService;
import com.dj.SpringBootJobsite.review.Review;
import com.dj.SpringBootJobsite.review.ReviewRepository;
import com.dj.SpringBootJobsite.review.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final CompanyService companyService;

    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        return reviewRepository.findByCompanyId(companyId);
    }

    @Override
    public boolean addReview(Long companyId, Review review) {
        Company company = companyService.getCompanyById(companyId);
        if (company == null) {
            return false;
        }
        review.setCompany(company);
        reviewRepository.save(review);
        return true;
    }

    @Override
    public Review getReviewById(Long companyId, Long reviewId) {
       List<Review> reviews = reviewRepository.findByCompanyId(companyId);
       return reviews.stream().filter(review -> review.getId().equals(reviewId)).findFirst().orElse(null);
    }

    @Override
    public boolean deleteReviewById(Long companyId, Long reviewId) {
       if(companyService.getCompanyById(companyId) == null) {
           return false;
       }

       if(reviewRepository.findById(reviewId).isEmpty()){
           return false;
       }
       Review review = reviewRepository.findById(reviewId).get();
       Company company = review.getCompany();
       company.getReviews().remove(review);
       reviewRepository.deleteById(reviewId);
       return true;
    }
}
