package dao;

import entity.Result;

import java.sql.SQLException;
import java.util.List;

public interface ResultDAO extends DAO<Result>{

    List<Result> findAllByUserId(int userId) throws SQLException;
}
