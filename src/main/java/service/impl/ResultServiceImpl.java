package service.impl;

import dao.ResultDAO;
import dao.impl.ResultDAOImpl;
import entity.Result;
import service.ResultService;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResultServiceImpl implements ResultService {

    private ResultDAO resultDAO = new ResultDAOImpl();

    public Result createResult(Result result) {
        try{
            result = resultDAO.create(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Result readResult(int id) {
        Result result = new Result();
        try{
            result = resultDAO.read(id);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void updateResult(Result result) {
        try{
            resultDAO.update(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteResult(int id) {
        try{
            resultDAO.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<Result> findAllByUserId(int userId) {
        List<Result> resultList = new ArrayList<Result>();
        try{
            resultList = resultDAO.findAllByUserId(userId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  resultList;
    }

    public List<Result> findAllResults() {
        List<Result> resultList = new ArrayList<Result>();
        try{
            resultList= resultDAO.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }
}
