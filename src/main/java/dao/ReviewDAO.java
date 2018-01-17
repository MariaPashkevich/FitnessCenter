package dao;

import entity.Review;

import java.util.List;

public interface ReviewDAO extends DAO<Review>{

    List<Review> findAllByCustomerId(int customerId);
    List<Review> findAllByTrainerId(int trainerId);
}
