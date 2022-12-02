package com.example.ecommerceapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.content.SharedPreferences;
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
import java.util.Map;

public class CartActivity extends AppCompatActivity {
    private Button checkoutButton;
    private Button continueShoppingButton;
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

        SharedPreferences sharedPreferences = getSharedPreferences("sharedPref", MODE_PRIVATE);
        ArrayList<CartItemModel> cartItemModelArrayList = new ArrayList<>();
        for(int i = 0; i < allItemsListings.size(); i++){
            Item temp = allItemsListings.get(i);
            int itemCount3 = sharedPreferences.getInt(String.valueOf(temp.getId()), 0);
            if(itemCount3 > 0){
                cartItemModelArrayList.add(new CartItemModel(temp.getId(), temp.getName(), temp.getPrice(), itemCount3));
            }
        }
        CartItemModel cartItemLst[] = new CartItemModel[cartItemModelArrayList.size()];
        for(int j = 0; j < cartItemModelArrayList.size(); j++){
            cartItemLst[j] = cartItemModelArrayList.get(j);
        }
        //TODO live data lst + cartitemadapter

        CartItemAdapter cartItemAdapter = new CartItemAdapter(cartItemLst, sharedPreferences);
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
        continueShoppingButton = findViewById(R.id.continueshoppingitems);
        continueShoppingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ItemsListings.class);
                startActivity(i);
            }
        });
    }
}

