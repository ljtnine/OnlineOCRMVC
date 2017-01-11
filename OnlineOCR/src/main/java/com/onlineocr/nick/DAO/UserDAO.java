package com.onlineocr.nick.DAO;

import com.onlineocr.nick.model.entity.User;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by GrIfOn on 08.01.2017.
 */
public interface UserDAO {
    void save(User user);
    void delete(User user);
    void deleteById(long id);
    void updateById(long id);
    void update(User user);
    List<User> getAll();
    User getById(long id);
    Session getCurrentSession();
}
