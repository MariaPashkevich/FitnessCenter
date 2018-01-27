package service;

import entity.Review;

import java.util.List;

public interface ReviewService {

    List<Review> findAllByCustomerId(int customerId);
    List<Review> findAllByTrainerId(int trainerId);
    Review createReview(Review review);
    Review readReview(int id);
    void updateReview(Review review);
    void deleteReview(int id);
    List<Review> findAllReviews();
}
