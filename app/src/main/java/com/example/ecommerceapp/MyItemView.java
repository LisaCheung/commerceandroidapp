package com.example.ecommerceapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecommerceapp.database.ItemsDAO;
import com.example.ecommerceapp.database.ItemsDB;
import com.example.ecommerceapp.database.entities.Item;
import com.google.android.material.button.MaterialButton;

import java.util.List;

public class MyItemView extends AppCompatActivity {
    MaterialButton backIcon;
    TextView itemName;
    TextView itemDescription;
    TextView itemPrice;
    ImageView itemImage;
    Button itemEdit;
    Button itemDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_item_view);

        backIcon = findViewById(R.id.back_icon3);
        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MyItemsGridView.class);
                startActivity(i);
            }
        });

        int itemIndex = getIntent().getIntExtra("itemIndex", 0);
        ItemsDB itemsDatabase = Room.databaseBuilder(getApplicationContext(),
                ItemsDB.class, "itemsDb").allowMainThreadQueries().build();
        ItemsDAO itemsDAO = itemsDatabase.itemsDAO();
        Item myItem = itemsDAO.getItemById(itemIndex);

        itemName = findViewById(R.id.myitemname2);
        itemName.setText(myItem.getName());

        itemDescription = findViewById(R.id.myitemdescrip2);
        if(myItem.getDescription() != null){
            itemDescription.setText(myItem.getDescription());
        }
        itemPrice = findViewById(R.id.myitemprice2);
        itemPrice.setText(String.valueOf(myItem.getPrice()));

        itemImage = findViewById(R.id.myitemimg2);

        itemEdit = findViewById(R.id.edit_item_btn);
        itemEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //direct to editItemUI
                Intent i = new Intent(getApplicationContext(), UpdateItemUI.class);
                i.putExtra("itemIndex", itemIndex);
                startActivity(i);
            }
        });

        itemDelete = findViewById(R.id.delete_item_btn);
        itemDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MyItemView.this)
                        .setIcon(R.drawable.ic_baseline_delete_24)
                        .setTitle("Delete " + myItem.getName())
                        .setMessage("Delete " + myItem.getName() +"?")
                        .setPositiveButton("Confirm", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                itemsDAO.deleteItem(myItem);
                                new AlertDialog.Builder(MyItemView.this)
                                        .setIcon(R.drawable.ic_baseline_delete_24)
                                        .setTitle("Item Deleted")
                                        .setMessage("Item successfully deleted.")
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener()
                                        {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which)
                                            {
                                                Intent i = new Intent(getApplicationContext(), MyItemsGridView.class);
                                                startActivity(i);
                                            }
                                        }).show();

                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                            }
                        }).show();

            }
        });

    }
}