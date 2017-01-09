package com.onlineocr.nick.DAO;

import com.onlineocr.nick.model.User;

import java.util.List;

/**
 * Created by GrIfOn on 08.01.2017.
 */
public interface UserDAO {
    void save(User user);
    void delete(User user);
    void deleteById(Long id);
    void updateById(Long id);
    void update(User user);
    List<User> selectAll();
    User selectById(Long id);
}
