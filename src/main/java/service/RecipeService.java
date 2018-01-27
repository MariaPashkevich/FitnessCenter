package service;

import entity.Recipe;

import java.util.List;

public interface RecipeService {

    Recipe createRecipe(Recipe recipe);
    Recipe readRecipe(int id);
    void updateRecipe(Recipe recipe);
    void deleteRecipe(int id);
    Recipe findByName(String name);
    List<Recipe> findAllRecipes();
}
