package com.example.ecommerceapp.database;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.ecommerceapp.database.entities.Item;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface ItemsDAO {
        @Insert
        public long addItem(Item item);

        @Update
        public void updateItem(Item item);

        @Delete
        public void deleteItem(Item item);

        @Query("select * from items_tbl")
        public List<Item> getAll();

        @Query("select * from items_tbl where item_id ==:itemId")
        public Item getItemById(long itemId);

        @Query("delete from items_tbl")
        public void deleteAll();

        @Query("delete from items_tbl where item_id==:itemId")
        public void deleteById(long itemId);
}
