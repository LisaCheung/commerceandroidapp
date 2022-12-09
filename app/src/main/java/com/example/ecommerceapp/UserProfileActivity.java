package com.example.ecommerceapp;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.ecommerceapp.database.UsersFirestore;
import com.example.ecommerceapp.database.entities.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

public class UserProfileActivity extends AppCompatActivity {
    private MaterialButton backButton;
    private ImageView profileImage;
    private EditText userName;
    private EditText userEmail;
    private EditText userAbout;
    private Button saveChanges;
    private Button deleteAccount;
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
        deleteAccount = findViewById(R.id.delete_account);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });
        String displayName= FirebaseAuth.getInstance().getCurrentUser().getEmail().split("[@]", 0)[0];
        FirebaseFirestore.getInstance().collection("Users").document(displayName).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                        Map<String, String> mp =(Map<String, String>) document.getData().get("user_info");
                        userName.setText(mp.get("name"));
                        userEmail.setText(mp.get("email"));
                        if(mp.get("about") != null){
                            userAbout.setText(mp.get("about"));
                        }

                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
//        User currUser = new UsersFirestore().getUserByName(displayName);
//        Log.i("displayName", currUser.getName());
//        userName.setText(currUser.getName());
//        userEmail.setText(currUser.getEmail());
//        if( currUser != null){
//            userAbout.setText(currUser.getAbout());
//        }

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

        deleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }
}