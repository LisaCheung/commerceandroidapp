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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignupActivity extends AppCompatActivity {
    private EditText signupEmail;
    private EditText signupPassword;
    private Button signupButton;
    private FirebaseAuth mAuth;
    private EditText confirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();

        signupEmail = findViewById(R.id.signupEmail);
        signupPassword = findViewById(R.id.signupPassword);
        signupButton = findViewById(R.id.signupButton);
        confirmPassword = findViewById(R.id.confirmPassword);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!confirmPassword.getText().toString().equals(signupPassword.getText().toString())){
                    Toast.makeText(getApplicationContext(), "Passwords do not match", Toast.LENGTH_LONG).show();
                    return;
                }
                mAuth.createUserWithEmailAndPassword(signupEmail.getText().toString(), signupPassword.getText().toString())
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                                    startActivity(i);
                                } else {
                                    Toast.makeText(getApplicationContext(), "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}