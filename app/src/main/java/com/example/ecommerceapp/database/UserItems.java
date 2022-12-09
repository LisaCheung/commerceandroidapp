package com.example.ecommerceapp.database;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.ecommerceapp.database.entities.Item;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

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
        hm.put("item_info", item);
        firestoreDB.collection("UsersListings").document(FirebaseAuth.getInstance().getCurrentUser().getEmail().split("[@]", 0)[0]).collection(Integer.toString(item.getId())).document("item_info").set(hm)
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

    }

    @Override
    public void updateUserItem(Item item) {

    }
}
