package service;

import entity.Trainer;

import java.util.List;

public interface TrainerService {

    Trainer createTrainer(Trainer trainer);
    Trainer readTrainer(int id);
    void updateTrainer(Trainer trainer);
    void deleteTrainer(int id);
    List<Trainer> findAllTrainers();
}
