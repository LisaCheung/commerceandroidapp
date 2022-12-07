package com.example.ecommerceapp.database;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.ecommerceapp.database.entities.Customer;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CustomerOrdersFirestore {
    FirebaseFirestore firestoreDB = FirebaseFirestore.getInstance();
    DocumentReference custOrdersRef = firestoreDB.collection("Customers").document("Orders");
    public void saveCustOrder(Customer customer, double totalPrice){
        Map<String, Object> hm = new HashMap<>();
        hm.put("customer_info", customer);
        hm.put("total_price", totalPrice);
        firestoreDB.collection("Customers")
                .document(customer.getName())
                .set(hm)
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

    public void deleteCustOrder(Customer customer){

    }

    public Customer readCustOrder(String custName){
        return null;
    }
    public void updateCustOrder(Customer customer, double totalPrice){

    }

}
