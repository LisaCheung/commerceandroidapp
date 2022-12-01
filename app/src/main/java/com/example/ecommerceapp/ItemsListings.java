package com.example.ecommerceapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.ecommerceapp.database.ItemsDAO;
import com.example.ecommerceapp.database.ItemsDB;
import com.example.ecommerceapp.database.entities.Item;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class ItemsListings extends AppCompatActivity {
    private GridView gridView;
    private ItemsDB itemsDatabase;
    private MaterialButton shoppingCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items_listings);
        shoppingCart = findViewById(R.id.shoppingcart);
        shoppingCart.setBackgroundColor(Color.GRAY);
        shoppingCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO : shopping cart fragment & checkout fragment
                Intent i = new Intent(getApplicationContext(), CartActivity.class);
                startActivity(i);
            }
        });
        itemsDatabase = Room.databaseBuilder(getApplicationContext(),
                ItemsDB.class, "itemsDb").allowMainThreadQueries().build();
        ItemsDAO itemsDAO = itemsDatabase.itemsDAO();
        List<Item> allItemsListings = itemsDAO.getAll();

        //TODO allItemsListings for gridview adapter
        //grid view item listings
        gridView = findViewById(R.id.gridview_itemlistings);
        ArrayList<Item> itemListings = new ArrayList<>();
        for(Item i: allItemsListings){
            itemListings.add(i);
        }
//        itemListings.add(new Item("item1", "descr1"));
//        itemListings.add(new Item("item2", "descr2"));
//        itemListings.add(new Item("item3", "descr3"));
        GridViewAdapter gridViewAdapter = new GridViewAdapter(this,itemListings);
        gridView.setAdapter(gridViewAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext() , itemListings.get(i).getName(), Toast.LENGTH_LONG).show();
                Log.i("TAG", itemListings.get(i).getName());
                //redirect to new activity putExtra to send data
//                MaterialButton plusButton = view.findViewById(R.id.plus_icon);
//                plusButton.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Toast.makeText(getApplicationContext(), "+ " + itemListings.get(i).getName(), Toast.LENGTH_LONG).show();
//                    }
//                });
                Intent i1 = new Intent(getApplicationContext(), ItemView1.class);
                int itemIndex = itemListings.get(i).getId();
                i1.putExtra("itemIndex", itemIndex);
                startActivity(i1);

            }
        });
    }
}