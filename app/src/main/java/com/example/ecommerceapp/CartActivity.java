package com.example.ecommerceapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ecommerceapp.cartitemslst.CartItemAdapter;
import com.example.ecommerceapp.cartitemslst.CartItemModel;
import com.example.ecommerceapp.database.ItemsDAO;
import com.example.ecommerceapp.database.ItemsDB;
import com.example.ecommerceapp.database.entities.Item;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    private Button checkoutButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        //temp data
        //TODO use cart lst data
        ItemsDB itemsDatabase = Room.databaseBuilder(getApplicationContext(),
                ItemsDB.class, "itemsDb").allowMainThreadQueries().build();
        ItemsDAO itemsDAO = itemsDatabase.itemsDAO();
        List<Item> allItemsListings = itemsDAO.getAll();
        CartItemModel cartItemLst[] = new CartItemModel[allItemsListings.size()];
        for(int i = 0; i < allItemsListings.size(); i++){
            Item temp = allItemsListings.get(i);
            cartItemLst[i] = new CartItemModel(temp.getName(), temp.getPrice(), 0);
        }
//        CartItemModel[] cartItemLst = new CartItemModel[]{
//new CartItemModel("name1", 1.23, 0),
//                new CartItemModel("name2", 50.0, 0),
//                new CartItemModel("name3", 5.25, 0)
//        };
        ItemCount itemCount = new ViewModelProvider(this).get(ItemCount.class);
        //TODO live data lst + cartitemadapter

        CartItemAdapter cartItemAdapter = new CartItemAdapter(cartItemLst);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(cartItemAdapter);
        checkoutButton = findViewById(R.id.checkoutButton);
        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), CheckoutActivity.class);
                startActivity(i);
            }
        });
    }
}