package com.example.ecommerceapp;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
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
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
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
                String originalEmail = user.getEmail();
                Log.i("origEmail", originalEmail);
                Log.i("updatedEmail", userEmail.getText().toString().trim());
                if(!originalEmail.equals(userEmail.getText().toString().trim())){
                    //TODO firebaseauth update email, delete doc from firestore, re-add
                    EditText updatePasswordET = new EditText(getApplicationContext());
                    new AlertDialog.Builder(UserProfileActivity.this)
                            .setIcon(R.drawable.ic_baseline_delete_24)
                            .setTitle("Re-enter credentials to update email:")
                            .setMessage("Enter password:")
                            .setView(updatePasswordET)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener()
                            {
                                @Override
                                public void onClick(DialogInterface dialog, int which)
                                {
                                    final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                    AuthCredential credential = EmailAuthProvider
                                            .getCredential(user.getEmail(), updatePasswordET.getText().toString().trim());
                                    user.reauthenticate(credential)
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    FirebaseUser user5 = FirebaseAuth.getInstance().getCurrentUser();
                                                    user5.updateEmail(userEmail.getText().toString())
                                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                @Override
                                                                public void onComplete(@NonNull Task<Void> task) {
                                                                    if (task.isSuccessful()) {
                                                                        Log.d(TAG, "user email updated");
                                                                        new UsersFirestore().deleteUserByEmailSubstring(originalEmail.split("[@]", 0)[0]);
                                                                        User updatedUser = new User();
                                                                        updatedUser.setEmail(userEmail.getText().toString());
                                                                        updatedUser.setName(userName.getText().toString());
                                                                        updatedUser.setAbout(userAbout.getText().toString());
                                                                        new UsersFirestore().addUser(updatedUser);
                                                                    }
                                                                }
                                                            });

                                                }
                                            });

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
                else{
                    //TODO update firestore, no email change
                    User updatedUser = new User();
                    updatedUser.setEmail(user.getEmail());
                    updatedUser.setName(userName.getText().toString());
                    updatedUser.setAbout(userAbout.getText().toString());
                    new UsersFirestore().updateUser(updatedUser);
                    new AlertDialog.Builder(UserProfileActivity.this)
                            .setIcon(R.drawable.ic_baseline_person_24)
                            .setTitle("Successfully updated")
                            .setMessage("Changes successfully saved")
                            .setNegativeButton("Close", new DialogInterface.OnClickListener()
                            {
                                @Override
                                public void onClick(DialogInterface dialog, int which)
                                {
                                }
                            }).show();

                }
            }
        });

        deleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //TODO: delete from firebase Auth
                EditText deleteET = new EditText(getApplicationContext());
                deleteET.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                new AlertDialog.Builder(UserProfileActivity.this)
                        .setIcon(R.drawable.ic_baseline_delete_24)
                        .setTitle("Confirm Deletion")
                        .setMessage("Enter password:")
                        .setView(deleteET)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                AuthCredential credential = EmailAuthProvider
                                        .getCredential(user.getEmail(), deleteET.getText().toString().trim());
                                user.reauthenticate(credential)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                user.delete()
                                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<Void> task) {
                                                                if (task.isSuccessful()) {
                                                                    new UsersFirestore().deleteUserByEmailSubstring(user.getEmail().split("[@]", 0)[0]);
                                                                    Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                                                                    startActivity(i);
                                                                }
                                                            }
                                                        });

                                            }
                                        });
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