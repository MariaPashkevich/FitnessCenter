package dao.impl;

import connection.ConnectionPool;
import dao.ReviewDAO;
import entity.Review;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReviewDAOImpl implements ReviewDAO {

    private static final String FIND_ALL_REVIEWS = "SELECT REVIEW_ID, CUSTOMER_ID, TRAINER_ID, `COMMENT` FROM REVIEW";
    private static final String FIND_ALL_REVIEWS_BY_CUSTOMER_ID = "SELECT REVIEW_ID, CUSTOMER_ID, TRAINER_ID, `COMMENT` FROM REVIEW WHERE CUSTOMER_ID=?";
    private static final String FIND_ALL_REVIEWS_BY_TRAINER_ID = "SELECT REVIEW_ID, CUSTOMER_ID, TRAINER_ID, `COMMENT` FROM REVIEW WHERE TRAINER_ID=?";
    private static final String CREATE_REVIEW = "INSERT INTO `REVIEW` (`CUSTOMER_ID`, `TRAINER_ID`, `COMMENT`) VALUES (?, ?, ?)";
    private static final String READ_REVIEW = "SELECT REVIEW_ID, CUSTOMER_ID, TRAINER_ID, `COMMENT` FROM REVIEW WHERE REVIEW_ID=?";
    private static final String UPDATE_REVIEW = "ConnectionPool";
    private static final String DELETE_REVIEW = "DELETE FROM REVIEW WHERE REVIEW_ID=?";


    public Review create(Review review) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(CREATE_REVIEW, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, review.getCustomerId());
            statement.setInt(2, review.getTrainerId());
            statement.setString(3, review.getComment());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if(resultSet.next()){
                review.setReviewId(resultSet.getInt(1));
            }
            resultSet.close();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return review;
    }

    public Review read(int id) throws SQLException {
        Review review = new Review();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(READ_REVIEW);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                review = reviewFromResultSet(resultSet);
            }
            resultSet.close();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return review;
    }

    public void update(Review review) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(UPDATE_REVIEW);
            statement.setString(1, review.getComment());
            statement.setInt(2, review.getReviewId());
            statement.executeUpdate();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }

    public void delete(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(DELETE_REVIEW);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }

    }

    public List<Review> findAll() throws SQLException {
        List<Review> reviewList = new ArrayList<Review>();
        Connection connection = null;
        Statement statement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_ALL_REVIEWS);
            while(resultSet.next()){
                reviewList.add(reviewFromResultSet(resultSet));
            }
            resultSet.close();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return reviewList;
    }

    public List<Review> findAllByCustomerId(int customerId) throws SQLException {
        List<Review> reviewList = new ArrayList<Review>();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(FIND_ALL_REVIEWS_BY_CUSTOMER_ID);
            statement.setInt(1, customerId);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                reviewList.add(reviewFromResultSet(resultSet));
            }
            resultSet.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return reviewList;
    }

    public List<Review> findAllByTrainerId(int trainerId) throws SQLException {
        List<Review> reviewList = new ArrayList<Review>();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(FIND_ALL_REVIEWS_BY_TRAINER_ID);
            statement.setInt(1, trainerId);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                reviewList.add(reviewFromResultSet(resultSet));
            }
            resultSet.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return reviewList;
    }

    private Review reviewFromResultSet(ResultSet resultSet) throws SQLException {
        Review review = new Review();
        review.setReviewId(resultSet.getInt("REVIEW_ID"));
        review.setCustomerId(resultSet.getInt("CUSTOMER_ID"));
        review.setTrainerId(resultSet.getInt("TRAINER_ID"));
        review.setComment(resultSet.getString("COMMENT"));
        return review;
    }
}
