package dao.impl;

import connection.ConnectionPool;
import dao.TrainerDAO;
import entity.Trainer;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TrainerDAOImpl implements TrainerDAO{

    private static final String FIND_ALL_TRAINERS = "SELECT TRAINER_ID, USER_ID, FIRST_NAME, LAST_NAME, SPECIALIZATION FROM TRAINER";
    private static final String CREATE_TRAINER = "INSERT INTO `TRAINER` (`USER_ID`,`FIRST_NAME`,`LAST_NAME`, `SPECIALIZATION`) VALUES (?, ?, ?, ?)";
    private static final String READ_TRAINER = "SELECT TRAINER_ID, USER_ID, FIRST_NAME, LAST_NAME, SPECIALIZATION FROM TRAINER WHERE TRAINER_ID=?";
    private static final String UPDATE_TRAINER = "UPDATE TRAINER SET SPECIALIZATION=? WHERE TRAINER_ID=?";
    private static final String DELETE_TRAINER = "DELETE FROM TRAINER WHERE TRAINER_ID=?";

    public Trainer create(Trainer trainer) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(CREATE_TRAINER, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, trainer.getUserId());
            statement.setString(2, trainer.getFisrtName());
            statement.setString(3, trainer.getLastName());
            statement.setString(4, trainer.getSpecialization());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if(resultSet.next()){
                trainer.setTrainerId(resultSet.getInt(1));
            }
            resultSet.close();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return trainer;
    }

    public Trainer read(int id) throws SQLException {
        Trainer trainer = new Trainer();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(READ_TRAINER);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                trainer = trainerFromResultSet(resultSet);
            }
            resultSet.close();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return trainer;
    }

    public void update(Trainer trainer) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(UPDATE_TRAINER);
            statement.setString(1, trainer.getSpecialization());
            statement.setInt(2, trainer.getTrainerId());
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
            statement = connection.prepareStatement(DELETE_TRAINER);
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

    public List<Trainer> findAll() throws SQLException {
        List<Trainer> trainerList = new ArrayList<Trainer>();
        Connection connection = null;
        Statement statement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_ALL_TRAINERS);
            while(resultSet.next()){
                trainerList.add(trainerFromResultSet(resultSet));
            }
            resultSet.close();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return trainerList;
    }

    private Trainer trainerFromResultSet(ResultSet resultSet) throws SQLException {
        Trainer trainer = new Trainer();
        trainer.setTrainerId(resultSet.getInt("TRAINER_ID"));
        trainer.setUserId(resultSet.getInt("USER_ID"));
        trainer.setFisrtName(resultSet.getString("FIRST_NAME"));
        trainer.setLastName(resultSet.getString("LAST_NAME"));
        trainer.setSpecialization(resultSet.getString("SPECIALIZATION"));
        return trainer;
    }
}
