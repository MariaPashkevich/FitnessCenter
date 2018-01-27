package service.impl;

import dao.MealDAO;
import dao.impl.MealDAOImpl;
import entity.Meal;
import service.MealService;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MealServiceImpl implements MealService {

    private MealDAO mealDAO = new MealDAOImpl();

    public List<Meal> getMealByCustomerId(int id) {
        List<Meal> mealList = new ArrayList<Meal>();
        try{
            mealList = mealDAO.findAllByUserId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mealList;
    }

    public Meal createMeal(Meal meal) {
        try{
            meal = mealDAO.create(meal);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return meal;
    }

    public Meal readMeal(int id) {
        Meal meal = new Meal();
        try{
            meal = mealDAO.read(id);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        return meal;
    }

    public void updateMeal(Meal meal) {
        try{
            mealDAO.update(meal);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteMeal(int id) {
        try{
            mealDAO.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Meal> findAllMeals() {
        List<Meal> mealList = new ArrayList<Meal>();
        try{
            mealList = mealDAO.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mealList;
    }
}
