package service.impl;

import entity.Training;
import org.testng.annotations.Test;
import service.TrainingService;

import java.util.List;

import static org.testng.Assert.*;

public class TrainingServiceImplTest {

    TrainingService trainingService = new TrainingServiceImpl();

    @Test
    public void testFindAllTrainings() throws Exception {
        List<Training> trainingList = trainingService.findAllTrainings();
        for(Training training: trainingList){
            System.out.println(training);
        }
    }

}