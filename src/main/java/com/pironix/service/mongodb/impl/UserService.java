package com.pironix.service.mongodb.impl;

import com.mongodb.BasicDBObject;
import com.pironix.dao.mongodb.UserDAO;
import com.pironix.model.mongodb.User;
import com.pironix.service.mongodb.UserServiceMongo;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

/**
 * @author ikarayel
 *         user Service
 */
public class UserService implements UserServiceMongo {
    UserDAO userDAO;
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }


    @Override
    public void addUser(User user) {
        userDAO.save(user);
    }

    @Override
    public void updateUser(User user, List<String> userParam) {
        if (!userParam.get(0).equals(user.getName()) && !userParam.get(0).equals(""))
            user.setName(userParam.get(0));
        if (!userParam.get(1).equals(user.getName()) && !userParam.get(1).equals(""))
            user.setSurname(userParam.get(1));
        if (!userParam.get(2).equals(user.getName()) && !userParam.get(2).equals(""))
            user.setPhone(userParam.get(2));
        userDAO.save(user);

    }

    @Override
    public void deleteUser(User user) {
        userDAO.delete(user);
    }

    @Override
    public User getUserById(int id) {
        return null;
    }

    @Override
    public List<User> getUsers() {
        return userDAO.find().asList();
    }
}
