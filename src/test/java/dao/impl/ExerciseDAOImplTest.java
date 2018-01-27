package dao.impl;

import dao.ExerciseDAO;
import entity.Exercise;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class ExerciseDAOImplTest {

    ExerciseDAO exerciseDAOImpl = new ExerciseDAOImpl();

    @Test
    public void testFindAll() throws Exception {

        List<Exercise> exerciseList = exerciseDAOImpl.findAll();
        for(Exercise ex: exerciseList){
            System.out.println(ex.toString());
        }
    }

}