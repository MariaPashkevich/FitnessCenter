package dao.impl;

import dao.TrainerDAO;
import entity.Trainer;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class TrainerDAOImplTest {

    TrainerDAO trainerDAO = new TrainerDAOImpl();

    @Test
    public void testFindAll() throws Exception {
        List<Trainer> trainerList = trainerDAO.findAll();
        for(Trainer trainer: trainerList){
            System.out.println(trainer);
        }
    }

}