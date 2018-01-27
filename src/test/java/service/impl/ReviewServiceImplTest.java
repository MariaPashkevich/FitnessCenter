package service.impl;

import entity.Review;
import org.testng.annotations.Test;
import service.ReviewService;

import java.util.List;

import static org.testng.Assert.*;

public class ReviewServiceImplTest {

    ReviewService reviewService = new ReviewServiceImpl();

    @Test
    public void testFindAllReviews() throws Exception {
        List<Review> reviewList = reviewService.findAllReviews();
        for(Review review: reviewList){
            System.out.println(review.toString());
        }
    }

}