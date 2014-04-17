package com.pironix.service.mongodb.impl;


import com.pironix.dao.mongodb.UserDAO;
import com.pironix.model.mongodb.User;
import org.bson.types.ObjectId;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author ikarayel
 *         4/13/14.
 *         unique test class
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/application-context.xml"})
@TransactionConfiguration(defaultRollback = false)
public class UserServiceTest {
    @Qualifier("userDAO")
    @Autowired()
    UserDAO userDAO;


    @org.junit.Test
    public void getUserTest() throws Exception {
        ObjectId objectId = ObjectId.massageToObjectId("534bed2f7dc3da8f110bcd79");
        User user = userDAO.get(objectId);
        assertNotNull(user);
        assertEquals(user.getName(), "Cemil");
        assertEquals(user.getSurname(), "Can");

    }

    @org.junit.Test
    public void setUserTest() throws Exception {

    }

    @org.junit.Test
    public void addUserTest() throws Exception {
        User user = new User();
        user.setName("Adem");
        user.setSurname("Kara");
        user.setPhone("05545553232");
        userDAO.save(user);
    }

    @org.junit.Test
    public void updateUserTest() throws Exception {
        ObjectId objectId = ObjectId.massageToObjectId("534c33465c364daaf3f01201");
        User user = userDAO.get(objectId);
        user.setName("Murat");
        userDAO.save(user);
        assertEquals(user.getName(), "Murat");


    }

    @org.junit.Test
    public void findOneEx() {
        Query query = new Query();
        ObjectId objectId = ObjectId.massageToObjectId("534bed2f7dc3da8f110bcd79");
        User user = userDAO.get(objectId);
    }

    @org.junit.Test
    public void deleteUserTest() throws Exception {
        ObjectId objectId = ObjectId.massageToObjectId("534ac7db2924928a2f074158");
        User user = userDAO.get(objectId);
        userDAO.delete(user);
        assertNull(userDAO.get(objectId));
    }

    @org.junit.Test
    public void getUserByIdTest() throws Exception {
        ObjectId objectId = ObjectId.massageToObjectId("534ac7db2924928a2f074158");
        User user = userDAO.get(objectId);
        assertNotNull(user);
    }


    @org.junit.Test
    public void getUsersTest() throws Exception {
        List<User> users = userDAO.find().asList();
        System.out.println(users.get(1).getId());
        assertEquals(3, users.size());
    }
}

