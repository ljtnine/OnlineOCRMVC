package com.onlineocr.nick.DAO;

import org.hibernate.Session;

import java.util.List;

/**
 * Created by GrIfOn on 11.01.2017.
 */
public interface DAO<T> {
    void save(T e);
    void delete(T e);
    void deleteById(long id);
    void updateById(long id);
    void update(T e);
    List<T> getAll();
    T getById(long id);
    Session getCurrentSession();
}
