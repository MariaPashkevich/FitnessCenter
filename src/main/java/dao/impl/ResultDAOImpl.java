package dao.impl;

import connection.DataSource;
import dao.ResultDAO;
import entity.Result;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ResultDAOImpl implements ResultDAO{

    private static final String FIND_ALL_RESULTS = "SELECT RESULT_ID, CUSTOMER_ID, WEIGHT, WAIST, THIGH, CHEST, HEIGHT, MEASUREMENT_DATE FROM RESULTS";
    private static final String FIND_RESULT_BY_USER_ID = "SELECT RESULT_ID, CUSTOMER_ID, WEIGHT, WAIST, THIGH, CHEST, HEIGHT, MEASUREMENT_DATE FROM RESULTS WHERE CUSTOMER_ID=?";
    private static final String CREATE_RESULT = "INSERT INTO `RESULTS` (`CUSTOMER_ID`, `WEIGHT`, `WAIST`, `THIGH`, `CHEST`, `HEIGHT`, `MEASUREMENT_DATE`) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String READ_RESULT = "SELECT RESULT_ID, CUSTOMER_ID, WEIGHT, WAIST, THIGH, CHEST, HEIGHT, MEASUREMENT_DATE FROM RESULTS WHERE RESULT_ID=?";
    private static final String UPDATE_RESULT = "UPDATE RESULTS SET WEIGHT=?, WAIST=?, THIGH=?, CHEST=?  WHERE RESULT_ID=?";
    private static final String DELETE_RESULT = "DELETE FROM RESULTS WHERE RESULT_ID=?";

    public List<Result> findAllByUserId(int userId) throws SQLException {
        List<Result> resultList = new ArrayList<Result>();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DataSource.getInstance().getConnection();
            statement = connection.prepareStatement(FIND_RESULT_BY_USER_ID);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                resultList.add(resultFromResultSet(resultSet));
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
        return resultList;
    }

    public Result create(Result result) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DataSource.getInstance().getConnection();
            statement = connection.prepareStatement(CREATE_RESULT, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, result.getCustomerId());
            statement.setDouble(2, result.getWeight());
            statement.setDouble(3, result.getWaist());
            statement.setDouble(4, result.getThigh());
            statement.setDouble(5, result.getChest());
            statement.setDouble(6, result.getHeight());
            statement.setDate(7, (Date) result.getMeasurementDate());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if(resultSet.next()){
                result.setCustomerId(resultSet.getInt(1));
            }
            resultSet.close();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return result;
    }

    public Result read(int id) throws SQLException {
        Result result = new Result();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DataSource.getInstance().getConnection();
            statement = connection.prepareStatement(READ_RESULT);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                result = resultFromResultSet(resultSet);
            }
            resultSet.close();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return result;
    }

    public void update(Result result) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DataSource.getInstance().getConnection();
            statement = connection.prepareStatement(UPDATE_RESULT);
            statement.setDouble(1, result.getWeight());
            statement.setDouble(2, result.getWaist());
            statement.setDouble(3, result.getThigh());
            statement.setDouble(4, result.getChest());
            statement.setInt(5, result.getResultId());
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
            statement = connection.prepareStatement(DELETE_RESULT);
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

    public List<Result> findAll() throws SQLException {
        List<Result> resultList = new ArrayList<Result>();
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DataSource.getInstance().getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_ALL_RESULTS);
            while(resultSet.next()){
                resultList.add(resultFromResultSet(resultSet));
            }
            resultSet.close();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return resultList;
    }

    private Result resultFromResultSet(ResultSet resultSet) throws SQLException {
        Result result = new Result();
        result.setResultId(resultSet.getInt("RESULT_ID"));
        result.setCustomerId(resultSet.getInt("CUSTOMER_ID"));
        result.setWeight(resultSet.getDouble("WEIGHT"));
        result.setWaist(resultSet.getDouble("WAIST"));
        result.setThigh(resultSet.getDouble("THIGH"));
        result.setChest(resultSet.getDouble("CHEST"));
        result.setHeight(resultSet.getDouble("HEIGHT"));
        result.setMeasurementDate(resultSet.getDate("MEASUREMENT_DATE"));
        return result;
    }
}
