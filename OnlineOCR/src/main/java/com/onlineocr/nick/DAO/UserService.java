package com.onlineocr.nick.DAO;

import com.onlineocr.nick.model.User;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by GrIfOn on 10.01.2017.
 */
@Repository
@Transactional
public class UserService implements UserDAO {
    //private static final Logger logger = Logger.getLogger(UserService.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(User user) {
        getCurrentSession().save(user);
    }

    @Override
    public void delete(User user) {

    }

    @Override
    public void deleteById(long id) {

    }

    @Override
    public void updateById(long id) {

    }

    @Override
    public void update(User user) {

    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User getById(long id) {
      //  logger.debug("Getting user. ID: " + id);
        User user = (User)getCurrentSession().load(User.class, id);
        return user;
    }

    @Override
    public Session getCurrentSession() {
      //  logger.debug("Getting current session");
        return sessionFactory.getCurrentSession();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
