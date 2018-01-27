package service.impl;

import entity.Meal;
import org.testng.annotations.Test;
import service.MealService;

import java.util.List;

import static org.testng.Assert.*;

public class MealServiceImplTest {

    MealService mealService = new MealServiceImpl();

    @Test
    public void testFindAllMeals() throws Exception {
        List<Meal> mealList = mealService.findAllMeals();
        for(Meal meal: mealList){
            System.out.println(meal.toString());
        }
    }
}