package dao;

import java.sql.SQLException;
import java.util.List;

public interface DAO <T>{

    T create(T t) throws SQLException;
    T read(int id) throws SQLException;
    void update(T t) throws  SQLException;
    void delete(int id) throws  SQLException;
    List<T> findAll() throws SQLException;
}
