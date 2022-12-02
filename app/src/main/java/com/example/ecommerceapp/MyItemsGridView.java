package com.example.ecommerceapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.ecommerceapp.database.ItemsDAO;
import com.example.ecommerceapp.database.ItemsDB;
import com.example.ecommerceapp.database.entities.Item;

import java.util.ArrayList;
import java.util.List;

public class MyItemsGridView extends AppCompatActivity {
    private GridView gridView;
    private ItemsDB itemsDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_items_grid_view);
        gridView = findViewById(R.id.gridview_myitems);
        //TODO my items db
        itemsDatabase = Room.databaseBuilder(getApplicationContext(),
                ItemsDB.class, "itemsDb").allowMainThreadQueries().build();
        ItemsDAO itemsDAO = itemsDatabase.itemsDAO();
        List<Item> allItemsListings = itemsDAO.getAll();
        ArrayList<Item> itemListings = new ArrayList<>();
        for(Item i: allItemsListings){
            itemListings.add(i);
        }
        GridViewAdapter gridViewAdapter = new GridViewAdapter(this,itemListings);
        gridView.setAdapter(gridViewAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent i1 = new Intent(getApplicationContext(), MyItemView.class);
                int itemIndex = itemListings.get(i).getId();
                i1.putExtra("itemIndex", itemIndex);
                startActivity(i1);

            }
        });


    }
}