package com.example.ecommerceapp.database;

import static android.content.ContentValues.TAG;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.ecommerceapp.database.entities.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class UsersFirestore implements UsersDBInterface{
    FirebaseFirestore firestoreDB =FirebaseFirestore.getInstance();
    public static User user1 = new User();
    @Override
    public User getUserByName(String userName) {
        firestoreDB.document(userName).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                        user1 = (User) document.getData().get("user_info");
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
        return user1;
    }

    @Override
    public void addUser(User user) {
        Map<String, Object> hm = new HashMap<>();
        hm.put("user_info", user);
        firestoreDB.collection("Users").document(user.getName()).set(hm)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }

    @Override
    public boolean deleteUserByName(String userName) {
        final boolean[] deleted = {false};
        firestoreDB.collection("Users").document(userName).delete() .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        deleted[0] = true;
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
        return deleted[0];
    }

    @Override
    public boolean updateUser(User user) {
        final boolean[] updated = {false};
        if(user.getName() == null || user.getName().isEmpty()){
            Log.i(TAG, "user name required for update");
            return false;
        }
        Map<String, Object> hm = new HashMap<>();
        hm.put("user_info", user);
        firestoreDB.collection("Users").document(user.getName()).update(hm)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        updated[0] = true;
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
        return updated[0];
    }
}
