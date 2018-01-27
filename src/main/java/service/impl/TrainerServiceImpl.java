package service.impl;

import dao.TrainerDAO;
import dao.impl.TrainerDAOImpl;
import entity.Trainer;
import service.TrainerService;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrainerServiceImpl implements TrainerService {

    private TrainerDAO trainerDAO = new TrainerDAOImpl();

    public Trainer createTrainer(Trainer trainer) {
        try{
            trainer = trainerDAO.create(trainer);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trainer;
    }

    public Trainer readTrainer(int id) {
        Trainer trainer = new Trainer();
        try{
            trainer = trainerDAO.read(id);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        return trainer;
    }

    public void updateTrainer(Trainer trainer) {
        try{
            trainerDAO.update(trainer);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteTrainer(int id) {
        try{
            trainerDAO.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Trainer> findAllTrainers() {
        List<Trainer> trainerList = new ArrayList<Trainer>();
        try{
            trainerList = trainerDAO.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trainerList;
    }
}
