package com.example.ecommerceapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecommerceapp.database.ItemsDAO;
import com.example.ecommerceapp.database.ItemsDB;
import com.example.ecommerceapp.database.entities.Item;

public class UpdateItemUI extends AppCompatActivity {
    private TextView updateHeader;
    private EditText itemName;
    private EditText itemDescription;
    private EditText itemPrice;
    private Button updateButton;
    private Button cancelButton;
    //TODO img & other item properties

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_item_ui);
        int itemIndex = getIntent().getIntExtra("itemIndex", 0);
        ItemsDB itemsDatabase = Room.databaseBuilder(getApplicationContext(),
                ItemsDB.class, "itemsDb").allowMainThreadQueries().build();
        ItemsDAO itemsDAO = itemsDatabase.itemsDAO();
        Item item = itemsDAO.getItemById(itemIndex);
        updateHeader = findViewById(R.id.textView5Update);
        updateHeader.setText("Update " + item.getName());

        itemName = findViewById(R.id.editTextItemNameUpdate);
        itemDescription = findViewById(R.id.editTextItemDescrUpdate);
        itemPrice = findViewById(R.id.editTextPriceUpdate);

        itemName.setText(item.getName());
        if(item.getDescription() != null){
            itemDescription.setText(item.getDescription());
        }
        itemPrice.setText(String.valueOf(item.getPrice()));

        updateButton = findViewById(R.id.update_button);
        cancelButton = findViewById(R.id.cancel_buttonUpdate);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newName = itemName.getText().toString();
                String newDescription = itemDescription.getText().toString();
                double newPrice = Double.parseDouble(itemPrice.getText().toString());
                Item newItem= new Item(newName, newDescription);
                newItem.setPrice(newPrice);
                newItem.setId(item.getId());
                itemsDAO.updateItem(newItem);
                new AlertDialog.Builder(UpdateItemUI.this)
                        .setIcon(R.drawable.ic_baseline_draw_24)
                        .setTitle("Update " + newItem.getName())
                        .setMessage(newItem.getName() + " successfully updated")
                        .setPositiveButton("CLOSE", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                Intent i = new Intent(getApplicationContext(), MyItemsGridView.class);
                                startActivity(i);
                            }
                        }).show();

            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MyItemsGridView.class);
                startActivity(i);
            }
        });
    }
}