package service.impl;

import dao.ReviewDAO;
import dao.impl.ReviewDAOImpl;
import entity.Review;
import service.ReviewService;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReviewServiceImpl implements ReviewService {

    private ReviewDAO reviewDAO = new ReviewDAOImpl();

    public List<Review> findAllByCustomerId(int customerId) {
        List<Review> reviewList = new ArrayList<Review>();
        try{
            reviewList = reviewDAO.findAllByCustomerId(customerId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reviewList;
    }

    public List<Review> findAllByTrainerId(int trainerId) {
        List<Review> reviewList = new ArrayList<Review>();
        try{
            reviewList = reviewDAO.findAllByCustomerId(trainerId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reviewList;    }

    public Review createReview(Review review) {
        try{
            review = reviewDAO.create(review);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return review;
    }

    public Review readReview(int id) {
        Review review = new Review();
        try{
            review = reviewDAO.read(id);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        return review;
    }

    public void updateReview(Review review) {
        try{
            reviewDAO.update(review);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteReview(int id) {
        try{
            reviewDAO.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Review> findAllReviews() {
        List<Review> reviewList = new ArrayList<Review>();
        try{
            reviewList = reviewDAO.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reviewList;
    }
}
