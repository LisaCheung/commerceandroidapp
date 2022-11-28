package com.example.ecommerceapp.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.ecommerceapp.database.entities.Item;

@Database(entities = {Item.class}, version=1)
public abstract class ItemsDB extends RoomDatabase {
    //link dao with database
    public abstract ItemsDAO todoDAO();
}
