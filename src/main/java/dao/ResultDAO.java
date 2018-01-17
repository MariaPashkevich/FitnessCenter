package dao;

import entity.Result;

public interface ResultDAO extends DAO<Result>{

    Result findAllByUserId(int userId);
}
