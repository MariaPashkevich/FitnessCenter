package dao;

import entity.Meal;

import java.util.List;

public interface MealDAO extends DAO<Meal>{

    List<Meal> findAllByUserId(int userId);
}
