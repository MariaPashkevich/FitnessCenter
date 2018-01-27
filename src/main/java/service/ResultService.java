package service;

import entity.Result;

import java.util.List;

public interface ResultService {

    Result createResult(Result result);
    Result readResult(int id);
    void updateResult(Result result);
    void deleteResult(int id);
    List<Result> findAllByUserId(int userId);
    List<Result> findAllResults();
}
