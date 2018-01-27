package service.impl;

import dao.TrainingDAO;
import dao.impl.TrainingDAOImpl;
import entity.Training;
import service.TrainingService;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrainingServiceImpl implements TrainingService {

    private TrainingDAO trainingDAO = new TrainingDAOImpl();

    public Training createTraining(Training training) {
        try{
            training = trainingDAO.create(training);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return training;
    }

    public Training readTraining(int id) {
        Training training = new Training();
        try{
            training = trainingDAO.read(id);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        return training;
    }

    public void updateTraining(Training training) {
        try{
            trainingDAO.update(training);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void deleteTraining(int id) {
        try{
            trainingDAO.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Training> findAllByCustomerId(int customerId) {
        List<Training> trainingList = new ArrayList<Training>();
        try{
            trainingList = trainingDAO.findAllByCustomerId(customerId);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trainingList;
    }

    public List<Training> findAllTrainings() {
        List<Training> trainingList = new ArrayList<Training>();
        try{
            trainingList = trainingDAO.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trainingList;
    }
}
