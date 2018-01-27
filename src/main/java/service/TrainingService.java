package service;

import entity.Training;

import java.util.List;

public interface TrainingService {

    Training createTraining(Training training);
    Training readTraining(int id);
    void updateTraining(Training training);
    void deleteTraining(int id);
    List<Training> findAllByCustomerId(int customerId);
    List<Training> findAllTrainings();
}
