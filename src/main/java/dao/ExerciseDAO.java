package dao;

import entity.Exercise;

import java.sql.SQLException;

public interface ExerciseDAO extends DAO<Exercise>{

    Exercise findByName(String name) throws SQLException;
}
