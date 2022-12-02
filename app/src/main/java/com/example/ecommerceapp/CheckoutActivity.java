package com.example.ecommerceapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecommerceapp.database.ItemsDAO;
import com.example.ecommerceapp.database.ItemsDB;

import java.util.Map;

public class CheckoutActivity extends AppCompatActivity {
    private Button submitButton;
    private TextView totalPrice;
    private ItemsDB itemsDatabase;
    private Button backButton;
    private EditText nameEditText;
    private EditText emailEditText;
    private EditText addressEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        //TODO
        nameEditText = findViewById(R.id.editTextTextPersonName);
        emailEditText = findViewById(R.id.editTextTextEmailAddress);
        addressEditText = findViewById(R.id.editTextTextPostalAddress);



        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), CartActivity.class));
            }
        });
        totalPrice = findViewById(R.id.totalprice);
//        SharedPreferences sharedPreferences = getSharedPreferences("sharedPref", MODE_PRIVATE);
        double totalPrice2 = getIntent().getDoubleExtra("totalPrice", 0);
        totalPrice.setText(String.valueOf(totalPrice2));
        submitButton = findViewById(R.id.submitPayment);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(CheckoutActivity.this)
                        .setIcon(R.drawable.ic_baseline_shopping_bag_24)
                        .setTitle("Thank you")
                        .setMessage("Order successfully processed. Thank you for shopping with us!")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(i);
                            }
                        }).show();
//                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener()
//                        {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which)
//                            {
//                            }
//                        })

            }
        });


    }
}