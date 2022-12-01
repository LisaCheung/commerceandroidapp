package com.example.ecommerceapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ecommerceapp.cartitemslst.CartItemAdapter;
import com.example.ecommerceapp.cartitemslst.CartItemModel;

public class CartActivity extends AppCompatActivity {
    private Button checkoutButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        //temp data
        //TODO use db data
        CartItemModel[] cartItemLst = new CartItemModel[]{
new CartItemModel("name1", 1.23, 0),
                new CartItemModel("name2", 50.0, 0),
                new CartItemModel("name3", 5.25, 0)
        };
        CartItemAdapter cartItemAdapter = new CartItemAdapter(cartItemLst);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(cartItemAdapter);
        /*
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        RecyclerViewModel[] lst = new RecyclerViewModel[]{
            new RecyclerViewModel("text1", R.drawable.app1),
                new RecyclerViewModel("text2", R.drawable.app1),
                new RecyclerViewModel("text3", R.drawable.app1)
        };
        CustomAdapter customAdapter = new CustomAdapter(lst);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(customAdapter);
         */
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