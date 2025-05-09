package com.tiendaropa.dao;

import java.util.List;

public interface Dao<T> {
    T findById(int id);
    List<T> findAll();
    int save(T t);
    boolean update(T t);
    boolean delete(int id);
}