package service.impl;

import entity.Trainer;
import org.testng.annotations.Test;
import service.TrainerService;

import java.util.List;

import static org.testng.Assert.*;

public class TrainerServiceImplTest {

    TrainerService trainerService = new TrainerServiceImpl();

    @Test
    public void testFindAllTrainers() throws Exception {
        List<Trainer> trainerList = trainerService.findAllTrainers();
        for(Trainer trainer: trainerList){
            System.out.println(trainer);
        }
    }

}