package com.example.ecommerceapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Index;
import androidx.room.Room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecommerceapp.database.ItemsDAO;
import com.example.ecommerceapp.database.ItemsDB;
import com.example.ecommerceapp.database.entities.Item;
import com.google.android.material.button.MaterialButton;

import java.util.List;

public class ItemView1 extends AppCompatActivity {
    private ItemsDB itemsDatabase;
    private TextView itemName;
    //ImageView
    private TextView itemDescription;
    private TextView itemPrice;
    private TextView itemQuantity;
    private ItemCount itemCount;
    private MaterialButton increaseCount;
    private MaterialButton decreaseCount;
    private MaterialButton backIcon;
    private ImageView imageView;

//       try {
//        URL url = new URL("https://cdn.cutithai.com/wp-content/uploads/furniture-elegant-small-writing-desk-home_1234483.jpg");
//        Bitmap image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
//    } catch(IOException e) {
//        System.out.println(e);
//    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_view1);
        backIcon = findViewById(R.id.back_icon2);
        backIcon.setBackgroundColor(Color.GRAY);
        imageView = findViewById(R.id.itemimg2);
        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),ItemsListings.class);
                //todo - increase shopping cart count  & total price shared pref
                startActivity(i);
            }
        });
        int itemIndex = getIntent().getIntExtra("itemIndex", 0);
        itemsDatabase = Room.databaseBuilder(getApplicationContext(),
                ItemsDB.class, "itemsDb").allowMainThreadQueries().build();
        ItemsDAO itemsDAO = itemsDatabase.itemsDAO();
//        List<Item> allItemsListings = itemsDAO.getAll();
//        Item item = allItemsListings.get(itemIndex);
        Item item = itemsDAO.getItemById(itemIndex);
        if(item.getItemImage() != null){
            new InternetImage2(imageView).execute(item.getItemImage());
        }
        itemName = findViewById(R.id.itemname2);
        itemDescription = findViewById(R.id.itemdescrip2);
        itemPrice = findViewById(R.id.itemprice2);
        itemName.setText(item.getName());
        itemQuantity = findViewById(R.id.itemQuantity2);
        increaseCount = findViewById(R.id.plus_icon2);
        decreaseCount = findViewById(R.id.minus_icon2);
        increaseCount.setBackgroundColor(Color.BLUE);
        decreaseCount.setBackgroundColor(Color.RED);
        itemCount = new ViewModelProvider(this).get(ItemCount.class);
        SharedPreferences sharedPreferences = getSharedPreferences("sharedPref", MODE_PRIVATE);
        if(sharedPreferences.contains(String.valueOf(itemIndex))){
            itemCount.setInitialCount(sharedPreferences.getInt(String.valueOf(itemIndex), 0));
        }
        LiveData<Integer> itemCount2 = itemCount.getInitialCount();
        itemQuantity.setText(String.valueOf(itemCount2.getValue()));
        itemCount2.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                itemQuantity.setText(String.valueOf(integer.intValue()));
            }
        });

        increaseCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemCount.increaseCount();
                increaseCartCount(itemIndex);
            }
        });
        decreaseCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemCount.decreaseCount();

            }
        });
        if(item.getDescription() != null){
            itemDescription.setText(item.getDescription());
        }
        itemPrice.setText("$" + String.valueOf(item.getPrice()));
//        Toast.makeText(getApplicationContext(), item.getName(), Toast.LENGTH_LONG).show();

    }

    private void increaseCartCount(int itemId){
        SharedPreferences sharedPreferences = getSharedPreferences("sharedPref", MODE_PRIVATE);
        int count= sharedPreferences.getInt(String.valueOf(itemId),0 );
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(String.valueOf(itemId),count + 1);
        editor.commit();
    }
    private void decreaseCartCount(int itemId){
        SharedPreferences sharedPreferences = getSharedPreferences("sharedPref", MODE_PRIVATE);
        int count= sharedPreferences.getInt(String.valueOf(itemId),0 );
        if(count > 0){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt(String.valueOf(itemId),count - 1);
            editor.commit();
        }
    }
}