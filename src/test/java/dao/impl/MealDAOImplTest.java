package dao.impl;

import dao.MealDAO;
import entity.Meal;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.List;

import static org.testng.Assert.*;

public class MealDAOImplTest {

    MealDAO mealDAOImpl = new MealDAOImpl();

    @Test
    public void testCreate() throws Exception {
        Meal meal = mealDAOImpl.create(new Meal(1, 1, 'b'));
        List<Meal> mealList = mealDAOImpl.findAllByUserId(1);
        for(Meal meals: mealList){
            System.out.println(meals.toString());
        }
    }

    @Test
    public void testRead() throws Exception {
        Meal meal = mealDAOImpl.read(1);
        System.out.println(meal);
    }

    @Test
    public void testUpdate() throws Exception {
        mealDAOImpl.update(new Meal(20, 2));
        List<Meal> mealList = mealDAOImpl.findAll();
        for(Meal meal: mealList){
            System.out.println(meal.toString());
        }
    }

    @Test
    public void testDelete() throws Exception {
        mealDAOImpl.delete(44);
        List<Meal> mealList = mealDAOImpl.findAllByUserId(1);
        for(Meal meals: mealList){
            System.out.println(meals.toString());
        }
    }

    @Test
    public void testFindAll() throws Exception {
        List<Meal> mealList = mealDAOImpl.findAll();
        for(Meal meal: mealList){
            System.out.println(meal.toString());
        }
    }

    @Test
    public void testFindAllByUserId() throws Exception {
        List<Meal> mealList = mealDAOImpl.findAllByUserId(1);
        for(Meal meal: mealList){
            System.out.println(meal.toString());
        }
    }

}