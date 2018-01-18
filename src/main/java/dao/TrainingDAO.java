package dao;

import entity.Training;

import java.sql.SQLException;
import java.util.List;

public interface TrainingDAO extends DAO<Training>{

    List<Training> findAllByCustomerId(int customerId) throws SQLException;
}
