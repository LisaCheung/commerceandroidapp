package com.example.ecommerceapp.database;

import com.example.ecommerceapp.database.entities.User;

public interface UsersDBInterface {
    public User getUserByName(String userName);
    public void addUser(User user);
    public boolean deleteUserByEmailSubstring(String userName);
    public boolean updateUser(User user);

}
