package dao;

import entity.Review;

import java.sql.SQLException;
import java.util.List;

public interface ReviewDAO extends DAO<Review>{

    List<Review> findAllByCustomerId(int customerId) throws SQLException;
    List<Review> findAllByTrainerId(int trainerId) throws SQLException;
}
