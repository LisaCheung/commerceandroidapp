package com.example.ecommerceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.ecommerceapp.database.UsersFirestore;
import com.example.ecommerceapp.database.entities.User;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserProfileActivity extends AppCompatActivity {
    private MaterialButton backButton;
    private ImageView profileImage;
    private EditText userName;
    private EditText userEmail;
    private EditText userAbout;
    private Button saveChanges;
    private FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        user = FirebaseAuth.getInstance().getCurrentUser();
        backButton = findViewById(R.id.user_backicon);
        profileImage = findViewById(R.id.user_img);
        userName= findViewById(R.id.user_name);
        userEmail = findViewById(R.id.user_email);
        userAbout = findViewById(R.id.user_about);
        saveChanges = findViewById(R.id.user_save_changes);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });
        User currUser = new UsersFirestore().getUserByName(user.getDisplayName());
        userName.setText(currUser.getName());
        userEmail.setText(currUser.getEmail());
        if(!currUser.getAbout().isEmpty() && currUser != null){
            userAbout.setText(currUser.getAbout());
        }

        profileImage.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                }
        );

        saveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }
}