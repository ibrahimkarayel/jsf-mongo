package com.pironix.service.mongodb;

import com.pironix.model.mongodb.User;

import java.util.List;

/**
 * @author ikarayel
 */
public interface UserServiceMongo {
    /**
     * Add User
     *
     * @param User user
     */
    public void addUser(User user);

    /**
     * Update User
     *
     * @param User user
     */
    public void updateUser(User user, List<String> userParam);

    /**
     * Delete User
     *
     * @param User user
     */
    public void deleteUser(User user);

    /**
     * Get User
     *
     * @param int User Id
     */
    public User getUserById(int id);

    /**
     * Get User List
     *
     * @return List - User list
     */
    public List<User> getUsers();
}
