package dao.impl;

import dao.ResultDAO;
import entity.Result;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class ResultDAOImplTest {

    ResultDAO resultDAO = new ResultDAOImpl();

    @Test
    public void testFindAll() throws Exception {
        List<Result> resultList = resultDAO.findAll();
        for(Result result: resultList){
            System.out.println(result.toString());
        }
    }

}