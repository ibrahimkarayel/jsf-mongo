package com.pironix.dao.mongodb;


import com.google.code.morphia.Morphia;
import com.google.code.morphia.dao.BasicDAO;
import com.pironix.model.mongodb.User;
import com.mongodb.Mongo;
import org.bson.types.ObjectId;


/*
*@author  ikarayel
* MongoDB User DAO
*
* */
public class UserDAO extends BasicDAO<User, ObjectId> {
    public UserDAO(Morphia morphia, Mongo mongo) {
        super(mongo, morphia, "pironix");
    }
}
