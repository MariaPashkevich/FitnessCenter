package dao.impl;

import connection.DataSource;
import dao.ExerciseDAO;
import entity.Exercise;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExerciseDAOImpl implements ExerciseDAO{

    private static final String FIND_ALL_EXERCISES = "SELECT EXERCISE_ID, NAME, DESCRIPTION, LINK FROM EXERCISE";
    private static final String FIND_BY_NAME = "SELECT EXERCISE_ID, NAME, DESCRIPTION, LINK FROM EXERCISE WHERE NAME=?";
    private static final String CREATE_EXERCISE = "INSERT INTO `EXERCISE` (`NAME`, `DESCRIPTION`, `LINK`) VALUES (?, ?, ?)";
    private static final String READ_EXERCISE = "SELECT EXERCISE_ID, NAME, DESCRIPTION, LINK FROM EXERCISE WHERE EXERCISE_ID=?";
    private static final String UPDATE_EXERCISE = "UPDATE EXERCISE SET NAME=?, DESCRIPTION=?, LINK=? WHERE EXERCISE_ID=?";
    private static final String DELETE_EXERCISE = "DELETE FROM EXERCISE WHERE EXERCISE_ID=?";

    public Exercise create(Exercise exercise) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DataSource.getInstance().getConnection();
            statement = connection.prepareStatement(CREATE_EXERCISE, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, exercise.getName());
            statement.setString(2, exercise.getDescription());
            statement.setString(3, exercise.getLink());

            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if(resultSet.next()){
                exercise.setExerciseId(resultSet.getInt(1));
            }
            resultSet.close();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            connection.close();
        }

        return exercise;
    }

    public Exercise read(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        Exercise exercise = new Exercise();
        try {
            connection = DataSource.getInstance().getConnection();
            statement = connection.prepareStatement(READ_EXERCISE);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                exercise = exerciseFromResultSet(resultSet);
            }
            resultSet.close();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return exercise;
    }

    public void update(Exercise exercise) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DataSource.getInstance().getConnection();
            statement = connection.prepareStatement(UPDATE_EXERCISE);
            statement.setString(1, exercise.getName());
            statement.setString(2, exercise.getDescription());
            statement.setString(3, exercise.getLink());
            statement.setInt(4, (int) exercise.getExerciseId());
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
            connection = DataSource.getInstance().getConnection();
            statement = connection.prepareStatement(DELETE_EXERCISE);
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

    public List<Exercise> findAll() throws SQLException {
        List<Exercise> exerciseList = new ArrayList<Exercise>();
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DataSource.getInstance().getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_ALL_EXERCISES);
            while(resultSet.next()){
                exerciseList.add(exerciseFromResultSet(resultSet));
            }
            resultSet.close();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return exerciseList;
    }

    public Exercise findByName(String name) throws SQLException {
        Exercise exercise = new Exercise();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DataSource.getInstance().getConnection();
            statement = connection.prepareStatement(FIND_BY_NAME);
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                exercise = exerciseFromResultSet(resultSet);
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
        return exercise;
    }

    private Exercise exerciseFromResultSet(ResultSet resultSet) throws SQLException {
        Exercise exercise = new Exercise();
        exercise.setExerciseId(resultSet.getInt("EXERCISE_ID"));
        exercise.setName(resultSet.getString("NAME"));
        exercise.setDescription(resultSet.getString("DESCRIPTION"));
        exercise.setLink(resultSet.getString("LINK"));
        return exercise;
    }
}
