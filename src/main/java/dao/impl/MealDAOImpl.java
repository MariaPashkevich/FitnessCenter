package dao.impl;

import connection.ConnectionPool;
import dao.MealDAO;
import entity.Meal;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MealDAOImpl implements MealDAO{

    private static final String FIND_ALL_MEALS = "SELECT MEAL_ID, CUSTOMER_ID, RECIPE_ID, TYPE, MEAL_DATE FROM MEAL";
    private static final String FIND_ALL_BY_CUSTOMER_ID = "SELECT MEAL_ID, CUSTOMER_ID, RECIPE_ID, TYPE, MEAL_DATE FROM MEAL WHERE CUSTOMER_ID=?";
    private static final String CREATE_MEAL = "INSERT INTO `MEAL` (`CUSTOMER_ID`, `RECIPE_ID`, `TYPE`) VALUES (?, ?, ?)";
    private static final String READ_MEAL = "SELECT MEAL_ID, CUSTOMER_ID, RECIPE_ID, `TYPE`, MEAL_DATE FROM MEAL WHERE MEAL_ID=?";
    private static final String UPDATE_MEAL = "UPDATE MEAL SET RECIPE_ID=? WHERE MEAL_ID=?";
    private static final String DELETE_MEAL = "DELETE FROM MEAL WHERE MEAL_ID=?";

    public Meal create(Meal meal) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(CREATE_MEAL, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, (int) meal.getCustomerId());
            statement.setInt(2, (int) meal.getRecipeId());
            statement.setString(3, String.valueOf(meal.getType()));
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if(resultSet.next()){
                meal.setMealId(resultSet.getInt(1));
            }
            resultSet.close();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return meal;
    }

    public Meal read(int id) throws SQLException {
        Meal meal = new Meal();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(READ_MEAL);
            statement.setInt(1, id);
            statement.executeQuery();
            ResultSet resultSet = statement.getResultSet();
            if (resultSet.next()){
                meal = mealFromResultSet(resultSet);
            }
            resultSet.close();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return meal;
    }

    public void update(Meal meal) throws SQLException {
        PreparedStatement statement = null;
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(UPDATE_MEAL);
            statement.setInt(1, (int) meal.getRecipeId());
            statement.setInt(2, (int) meal.getMealId());
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
            statement = connection.prepareStatement(DELETE_MEAL);
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

    public List<Meal> findAll() throws SQLException {
        List<Meal> mealList = new ArrayList<Meal>();
        Connection connection = null;
        Statement statement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_ALL_MEALS);
            while(resultSet.next()){
                mealList.add(mealFromResultSet(resultSet));
            }
            statement.close();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            connection.close();
        }
        return mealList;
    }

    public List<Meal> findAllByUserId(int userId) throws SQLException {
        List<Meal> mealList = new ArrayList<Meal>();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(FIND_ALL_BY_CUSTOMER_ID);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                mealList.add(mealFromResultSet(resultSet));
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
        return mealList;
    }

    private Meal mealFromResultSet(ResultSet resultSet) throws SQLException {
        Meal meal = new Meal();
        meal.setMealId(resultSet.getInt("MEAL_ID"));
        meal.setCustomerId(resultSet.getInt("CUSTOMER_ID"));
        meal.setRecipeId(resultSet.getInt("RECIPE_ID"));
        meal.setType(resultSet.getString("TYPE").charAt(0));
        meal.setMealDate(resultSet.getDate("MEAL_DATE"));
        return meal;
    }
}
