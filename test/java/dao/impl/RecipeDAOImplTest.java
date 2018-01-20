package dao.impl;

import dao.RecipeDAO;
import entity.Recipe;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class RecipeDAOImplTest {

    RecipeDAO recipeDAO = new RecipeDAOImpl();

    @Test
    public void testFindAll() throws Exception {
        List<Recipe> recipeList  = recipeDAO.findAll();
        for(Recipe recipe: recipeList){
            System.out.println(recipe.toString());
        }
    }

}