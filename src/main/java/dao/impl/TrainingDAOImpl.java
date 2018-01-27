package dao.impl;

import connection.ConnectionPool;
import dao.TrainerDAO;
import dao.TrainingDAO;
import entity.Training;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TrainingDAOImpl implements TrainingDAO{

    private static final String FIND_ALL_TRAININGS = "SELECT TRAINING_ID, CUSTOMER_ID, EXERCISE_ID, `NUMBER`, WEIGHT, TRAINING_DATE, `STATUS` FROM TRAINING";
    private static final String FIND_ALL_TRAININGS_BY_CUSTOMER_ID = "SELECT TRAINING_ID, CUSTOMER_ID, EXERCISE_ID, `NUMBER`, WEIGHT, TRAINING_DATE, `STATUS` FROM TRAINING WHERE CUSTOMER_ID=?";
    private static final String CREATE_TRAINING = "INSERT INTO `TRAINING` (`CUSTOMER_ID`, `EXERCISE_ID`, `NUMBER`, `TRAINING_DATE`, `STATUS`) VALUES (?, ?, ?, ?, ?)";
    private static final String READ_TRAINING = "SELECT TRAINING_ID, CUSTOMER_ID, EXERCISE_ID, `NUMBER`, WEIGHT, TRAINING_DATE, `STATUS` FROM TRAINING WHERE TRAINING_ID=?";
    private static final String UPDATE_TRAINING = "UPDATE TRAINING SET EXERCISE_ID=?, NUMBER=? TRAINING_DATE=?, STATUS=? WHERE TRAINING_ID=?";
    private static final String DELETE_TRAINING = "DELETE FROM TRAINING WHERE TRAINING_ID=?";

    public Training create(Training training) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(UPDATE_TRAINING, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, training.getCustomerId());
            statement.setInt(2, training.getExerciseId());
            statement.setInt(3, training.getNumber());
            statement.setInt(4, training.getWeight());
            statement.setDate(5, (Date) training.getTrainingDate());
            statement.setBoolean(6, training.isStatus());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if(resultSet.next()){
                training.setTrainingId(resultSet.getInt(1));
            }
            resultSet.close();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return training;
    }

    public Training read(int id) throws SQLException {
        Training training = new Training();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(READ_TRAINING);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                training = trainingFromResultSet(resultSet);
            }
            resultSet.close();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return training;
    }

    public void update(Training training) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(CREATE_TRAINING);
            statement.setInt(1, training.getExerciseId());
            statement.setInt(2, training.getNumber());
            statement.setDate(3, (Date) training.getTrainingDate());
            statement.setBoolean(4, training.isStatus());
            statement.setInt(5, training.getTrainingId());
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
            statement = connection.prepareStatement(DELETE_TRAINING);
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

    public List<Training> findAll() throws SQLException {
        List<Training> trainingList = new ArrayList<Training>();
        Connection connection = null;
        Statement statement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_ALL_TRAININGS);
            while (resultSet.next()){
                trainingList.add(trainingFromResultSet(resultSet));
            }
            resultSet.close();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return trainingList;
    }

    public List<Training> findAllByCustomerId(int customerId) throws SQLException {
        List<Training> trainingList = new ArrayList<Training>();
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(FIND_ALL_TRAININGS_BY_CUSTOMER_ID);
            statement.setInt(1, customerId);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                trainingList.add(trainingFromResultSet(resultSet));
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
        return trainingList;
    }

    private Training trainingFromResultSet(ResultSet resultSet) throws SQLException {
        Training training = new Training();
        training.setTrainingId(resultSet.getInt("TRAINING_ID"));
        training.setCustomerId(resultSet.getInt("CUSTOMER_ID"));
        training.setExerciseId(resultSet.getInt("EXERCISE_ID"));
        training.setNumber(resultSet.getInt("NUMBER"));
        training.setWeight(resultSet.getInt("WEIGHT"));
        training.setTrainingDate(resultSet.getDate("TRAINING_DATE"));
        training.setStatus(resultSet.getBoolean("STATUS"));
        return training;
    }
}
