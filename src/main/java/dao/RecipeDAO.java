package dao;

import entity.Recipe;

import java.sql.SQLException;

public interface RecipeDAO extends DAO<Recipe>{

    Recipe findByName(String name) throws SQLException;
}
