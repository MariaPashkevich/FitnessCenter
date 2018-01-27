package service.impl;

import entity.Exercise;
import org.testng.annotations.Test;
import service.ExerciseService;

import java.util.List;

import static org.testng.Assert.*;

public class ExerciseServiceImplTest {

    ExerciseService exerciseService = new ExerciseServiceImpl();

    @Test
    public void testFindAllExercises() throws Exception {

        List<Exercise> exerciseList = exerciseService.findAllExercises();
        for(Exercise ex: exerciseList){
            System.out.println(ex.toString());
        }
    }
}