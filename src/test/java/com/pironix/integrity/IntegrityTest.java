package com.pironix.integrity;

import com.google.code.morphia.Morphia;
import com.mongodb.Mongo;
import com.pironix.dao.mongodb.UserDAO;
import com.pironix.model.mongodb.User;
import org.bson.types.ObjectId;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;
import org.springframework.test.annotation.ExpectedException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import javax.jws.soap.SOAPBinding;

/**
 * Created by ikarayel on 4/16/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/application-context.xml"})
public class IntegrityTest extends AbstractDependencyInjectionSpringContextTests {
    // this instance will be (automatically) dependency injected
    private UserDAO userDAO;

    @Before
    public void initialize() throws Exception {
        super.setUp();
    }

    @After
    public void cleanup() throws Exception {
        super.tearDown();
    }

    // a setter method to enable DI of the 'userDao' instance variable
    public void setUserDao(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Test
    @ExpectedException(NullPointerException.class)
    public void nullCheckTest() {
//        ObjectId objectId = ObjectId.massageToObjectId("534bed2f7dc3da8f110bcd79");
        User user = userDAO.get(null);
    }
}