package dao.impl;

import dao.ReviewDAO;
import entity.Review;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class ReviewDAOImplTest {

    ReviewDAO reviewDAO = new ReviewDAOImpl();

    @Test
    public void testFindAll() throws Exception {
        List<Review> reviewList = reviewDAO.findAll();
        for(Review review: reviewList){
            System.out.println(review.toString());
        }
    }

}