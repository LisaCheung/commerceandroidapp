package com.example.ecommerceapp.database;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.ecommerceapp.database.entities.Item;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserItems implements UserItemListingsDBInterface{
    FirebaseFirestore firestoreDB =FirebaseFirestore.getInstance();
    @Override
    public List<Item> getAllUserItems() {
        return null;
    }

    @Override
    public Item findById(int id) {
        return null;
    }

    @Override
    public void addUserItem(Item item) {
        Map<String,Item> hm = new HashMap<>();
        hm.put(Integer.toString(item.getId()), item);
        firestoreDB.collection("UsersListings").document(FirebaseAuth.getInstance().getCurrentUser().getEmail().split("[@]", 0)[0]).set(hm, SetOptions.merge())
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.i("userListingAdd", "success");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i("userListingAdd", "fail");
                    }
                });
    }

    @Override
    public void deleteUserItem(int itemId) {
        //TODO delete by id
        Map<String, Object> hm = new HashMap<>();
        hm.put(Integer.toString(itemId), FieldValue.delete());
        firestoreDB.collection("UsersListings").document(FirebaseAuth.getInstance().getCurrentUser().getEmail().split("[@]", 0)[0]).update(hm)
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
    public void updateUserItem(Item item) {

    }
}
