package dao.impl;

import connection.ConnectionPool;
import dao.RecipeDAO;
import entity.Recipe;
import entity.Result;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecipeDAOImpl implements RecipeDAO{

    private static final String FIND_RECIPE_BY_NAME = "SELECT RECIPE_ID, `NAME`, DESCRIPTION, LINK FROM RECIPE WHERE NAME=?";
    private static final String FIND_ALL_RECIPES = "SELECT RECIPE_ID, NAME, DESCRIPTION, LINK FROM RECIPE";
    private static final String CREATE_RECIPE = "INSERT INTO `RECIPE` (`NAME`, `DESCRIPTION`, `LINK`) VALUES (?, ?, ?)";
    private static final String READ_RECIPE = "SELECT RECIPE_ID, NAME, DESCRIPTION, LINK FROM RECIPE WHERE RECIPE_ID=?";
    private static final String UPDATE_RECIPE = "UPDATE RECIPE SET DESCRIPTION=?, LINK=? WHERE RECIPE_ID=?";
    private static final String DELETE_RECIPE = "DELETE FROM RECIPE WHERE RECIPE_ID=?";

    public Recipe findByName(String name) throws SQLException {
        Recipe recipe = new Recipe();
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(FIND_RECIPE_BY_NAME);
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                recipe = recipeFromResultSet(resultSet);
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

        return recipe;
    }

    public Recipe create(Recipe recipe) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(CREATE_RECIPE, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, recipe.getName());
            statement.setString(2, recipe.getDescription());
            statement.setString(3, recipe.getLink());

            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if(resultSet.next()){
                recipe.setRecipeId(resultSet.getInt(1));
            }
            resultSet.close();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return  recipe;
    }

    public Recipe read(int id) throws SQLException {
        Recipe recipe = new Recipe();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(READ_RECIPE);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                recipe = recipeFromResultSet(resultSet);
            }
            resultSet.close();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return recipe;
    }


    public void update(Recipe recipe) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(UPDATE_RECIPE);
            statement.setString(1, recipe.getDescription());
            statement.setString(2, recipe.getLink());
            statement.setInt(3, recipe.getRecipeId());
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
            statement = connection.prepareStatement(DELETE_RECIPE);
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

    public List<Recipe> findAll() throws SQLException {
        List<Recipe> recipeList = new ArrayList<Recipe>();
        Connection connection = null;
        Statement statement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_ALL_RECIPES);
            while(resultSet.next()){
                recipeList.add(recipeFromResultSet(resultSet));
            }
            resultSet.close();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }

        return recipeList;
    }


    private Recipe recipeFromResultSet(ResultSet resultSet) throws SQLException {
        Recipe recipe = new Recipe();
        recipe.setRecipeId(resultSet.getInt("RECIPE_ID"));
        recipe.setName(resultSet.getString("NAME"));
        recipe.setDescription(resultSet.getString("DESCRIPTION"));
        recipe.setLink(resultSet.getString("LINK"));
        return recipe;
    }
}
