package dao;

import entity.Recipe;

public interface RecipeDAO extends DAO<RecipeDAO>{

    Recipe findByName(String name);
}
