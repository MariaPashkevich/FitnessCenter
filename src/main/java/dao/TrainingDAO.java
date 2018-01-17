package dao;

import entity.Training;

import java.util.List;

public interface TrainingDAO extends DAO<Training>{

    List<Training> findAllByCustomerId(int customerId);
    List<Training> findAllByTrainerId(int trainerId);
}
