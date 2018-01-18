package dao;

import entity.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CustomerDAO extends DAO<Customer>{

    List<Customer> findAllByGender(boolean gender) throws SQLException;
}
