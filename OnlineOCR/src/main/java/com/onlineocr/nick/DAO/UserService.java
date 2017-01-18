package com.onlineocr.nick.DAO;

import com.onlineocr.nick.model.entity.User;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
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
    private static final Logger logger = Logger.getLogger(UserService.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(User user) {
        logger.info("Saving user: " + user);
        getCurrentSession().save(user);
    }

    @Override
    public void delete(User user) {
        logger.info("Deleting user: " + user);
        getCurrentSession().delete(user);

    }

    @Override
    public void deleteById(long id) {
        logger.info("Deleting user by id: " + id);
        User user = getCurrentSession().load(User.class, id);
        getCurrentSession().delete(user);
    }

    @Override
    public void update(User user) {
        logger.info("Updating user: " + user);
        getCurrentSession().update(user);
    }

    @Override
    public List<User> getAll() {
        logger.info("Getting all users");
        return getCurrentSession().createCriteria(User.class).list();
    }

    @Override
    public User getById(long id) {
        logger.debug("Getting user. ID: " + id);
        User user = getCurrentSession().load(User.class, id);
        return user;
    }

    @Override
    public User getUserByLoginAndPassword(String login, String password) {
        User user = (User) getCurrentSession().createCriteria(User.class).
                add(Restrictions.eq("login", login)).
                add(Restrictions.eq("passwordHash", password)).uniqueResult();
        return user;
    }

    @Override
    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
