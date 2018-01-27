package service.impl;

import dao.ExerciseDAO;
import dao.impl.ExerciseDAOImpl;
import entity.Exercise;
import service.ExerciseService;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExerciseServiceImpl implements ExerciseService {

    private ExerciseDAO exerciseDAO = new ExerciseDAOImpl();

    public Exercise createExercise(Exercise exercise) {
        try{
            exercise = exerciseDAO.create(exercise);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exercise;
    }

    public Exercise readExercise(int id) {
        Exercise exercise = new Exercise();
        try{
            exercise = exerciseDAO.read(id);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        return exercise;
    }

    public void updateExercise(Exercise exercise) {
        try{
            exerciseDAO.update(exercise);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteExercise(int id) {
        try{
            exerciseDAO.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Exercise findByName(String name) {
        Exercise exercise = new Exercise();
        try{
            exercise = exerciseDAO.findByName(name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exercise;
    }

    public List<Exercise> findAllExercises() {
        List<Exercise> exerciseList = new ArrayList<Exercise>();
        try{
            exerciseList = exerciseDAO.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exerciseList;
    }
}

