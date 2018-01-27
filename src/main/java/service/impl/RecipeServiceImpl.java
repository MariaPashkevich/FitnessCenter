package service.impl;

import dao.RecipeDAO;
import dao.impl.RecipeDAOImpl;
import entity.Recipe;
import service.RecipeService;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecipeServiceImpl implements RecipeService {

    private RecipeDAO recipeDAO = new RecipeDAOImpl();

    public Recipe createRecipe(Recipe recipe) {
        try{
            recipe = recipeDAO.create(recipe);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipe;
    }

    public Recipe readRecipe(int id) {
        Recipe recipe = new Recipe();
        try{
            recipe = recipeDAO.read(id);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        return recipe;
    }

    public void updateRecipe(Recipe recipe) {
        try{
            recipeDAO.update(recipe);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteRecipe(int id) {
        try{
            recipeDAO.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Recipe findByName(String name) {
        Recipe recipe = new Recipe();
        try{
            recipe = recipeDAO.findByName(name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipe;
    }

    public List<Recipe> findAllRecipes() {
        List<Recipe> recipesList = new ArrayList<Recipe>();
        try{
            recipesList = recipeDAO.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipesList;
    }
}
