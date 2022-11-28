package com.example.ecommerceapp.database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.math.BigDecimal;

@Entity(tableName = "items_tbl")
public class Item {

    @ColumnInfo(name="item_id")
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name="item_name")
    private String name;
    @ColumnInfo(name="item_descr")
    private String description;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @ColumnInfo(name="item_price")
    private double price;

    //TODO: category enum
    @ColumnInfo(name="item_category")
    private String category;


    @Ignore
    public Item(){

    }
    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
