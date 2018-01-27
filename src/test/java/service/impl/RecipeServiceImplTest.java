package service.impl;

import entity.Recipe;
import org.testng.annotations.Test;
import service.RecipeService;

import java.util.List;

import static org.testng.Assert.*;

public class RecipeServiceImplTest {

    RecipeService recipeService = new RecipeServiceImpl();

    @Test
    public void testFindAllRecipes() throws Exception {
        List<Recipe> recipeList  = recipeService.findAllRecipes();
        for(Recipe recipe: recipeList){
            System.out.println(recipe.toString());
        }
    }

}