package dao.impl;

import dao.TrainingDAO;
import entity.Training;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class TrainingDAOImplTest {

    TrainingDAO trainingDAO = new TrainingDAOImpl();

    @Test
    public void testFindAll() throws Exception {
        List<Training> trainingList = trainingDAO.findAll();
        for(Training training: trainingList){
            System.out.println(training);
        }
    }

}