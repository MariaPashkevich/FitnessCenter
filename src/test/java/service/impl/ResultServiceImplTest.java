package service.impl;

import entity.Result;
import org.testng.annotations.Test;
import service.ResultService;

import java.util.List;

import static org.testng.Assert.*;

public class ResultServiceImplTest {

    ResultService resultService = new ResultServiceImpl();

    @Test
    public void testFindAllResults() throws Exception {
        List<Result> resultList = resultService.findAllResults();
        for(Result result: resultList){
            System.out.println(result.toString());
        }
    }

}