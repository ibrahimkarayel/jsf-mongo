package com.pironix.model.mongodb;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.sun.istack.internal.NotNull;
import org.bson.types.ObjectId;

/*
*
 *@autor ikarayel
 * User Table
 * */
@Entity("users")
public class User {
    @Id
    @NotNull
    ObjectId id; // auto-generated
    String name;
    String surname;

    String phone;

    public User() {

    }
    public User(ObjectId id,String name,String surname) {
        this.id=id;
        this.name=name;
        this.surname=surname;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


}
