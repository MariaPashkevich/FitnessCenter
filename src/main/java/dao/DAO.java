package dao;

import java.util.List;

public interface DAO <T>{

    T create(T t);
    T read(int id);
    void update(T t);
    void delete(T t);
    List<T> findAll();
}
