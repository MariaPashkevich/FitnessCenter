package dao;

import entity.Meal;

import java.sql.SQLException;
import java.util.List;

public interface MealDAO extends DAO<Meal>{

    List<Meal> findAllByUserId(int userId) throws SQLException;
}
