package com.example.ecommerceapp.database;

import com.example.ecommerceapp.database.entities.Item;

import java.util.List;

public interface UserItemListingsDBInterface {
    public List<Item> getAllUserItems();
    public Item findById(int id);
    public void addUserItem(Item item);
    public void deleteUserItem(int itemId);
    public void updateUserItem(Item item);
}
