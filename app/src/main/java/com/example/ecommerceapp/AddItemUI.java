package com.example.ecommerceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddItemUI extends AppCompatActivity {
    private Button submitButton;
    private Button cancelButton;
    private EditText itemName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item_ui);
        itemName = findViewById(R.id.editTextItemName);
        String inputName = itemName.getText().toString();
        cancelButton = findViewById(R.id.cancel_button);
        cancelButton.setBackgroundColor(Color.BLACK);
        submitButton = findViewById(R.id.submit_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputName = itemName.getText().toString();
                Toast.makeText(getApplicationContext(), "name" + inputName, Toast.LENGTH_SHORT).show();
            }
        });

    }
}