package dao;

import entity.Exercise;

public interface ExerciseDAO extends DAO<Exercise>{

    Exercise findByName(String name);
}
