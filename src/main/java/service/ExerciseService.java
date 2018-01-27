package service;

import entity.Exercise;

import java.sql.SQLException;
import java.util.List;

public interface ExerciseService {

    Exercise createExercise(Exercise exercise);
    Exercise readExercise(int id);
    void updateExercise(Exercise exercise);
    void deleteExercise(int id);
    Exercise findByName(String name);
    List<Exercise> findAllExercises();
}
