package service;

import entity.Meal;

import java.util.List;

public interface MealService {

    List<Meal> getMealByCustomerId(int id);
    Meal createMeal(Meal meal);
    Meal readMeal(int id);
    void updateMeal(Meal meal);
    void deleteMeal(int id);
    List<Meal> findAllMeals();
}
