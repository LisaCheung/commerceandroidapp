package com.example.ecommerceapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ecommerceapp.database.ItemsDAO;
import com.example.ecommerceapp.database.ItemsDB;
import com.example.ecommerceapp.database.entities.Item;

public class AddItemUI extends AppCompatActivity {
    private Button submitButton;
    private Button cancelButton;
    private EditText itemName;
    private EditText itemDescription;
    private ItemsDB itemsDatabase;
    private EditText itemPrice;
    //TODO same form for add & update
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item_ui);
        itemName = findViewById(R.id.editTextItemName);
        String inputName = itemName.getText().toString();
        itemDescription = findViewById(R.id.editTextItemDescr);
        itemPrice = findViewById(R.id.editTextPrice);
        cancelButton = findViewById(R.id.cancel_button);
        cancelButton.setBackgroundColor(Color.BLACK);
        submitButton = findViewById(R.id.submit_button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(itemName.getText().toString()) || TextUtils.isEmpty(itemPrice.getText().toString())){
                    Toast.makeText(getApplicationContext(), "item name & price required", Toast.LENGTH_LONG).show();
                    return;
                }
                double inputPrice = Double.parseDouble(itemPrice.getText().toString());
                String inputName = itemName.getText().toString();
                String inputDescription = itemDescription.getText().toString();
                Item item = new Item(inputName, inputDescription);
                item.setPrice(inputPrice);
                Log.i("PRICE", String.valueOf(item.getPrice()));
                itemsDatabase = Room.databaseBuilder(getApplicationContext(), ItemsDB.class, "itemsDb").allowMainThreadQueries().build();
                ItemsDAO itemsDAO = itemsDatabase.itemsDAO();
                itemsDAO.addItem(item);
                for(Item i: itemsDAO.getAll()){
                    Log.i("ITEM", i.getName());
                }
//        ArrayList<Item> allItemsListings = itemsDAO.getAll();
                Toast.makeText(getApplicationContext(), "name" + inputName, Toast.LENGTH_SHORT).show();
            }
        });

    }
}