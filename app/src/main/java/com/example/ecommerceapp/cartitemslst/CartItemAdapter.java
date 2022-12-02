package com.example.ecommerceapp.cartitemslst;

import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecommerceapp.CartActivity;
import com.example.ecommerceapp.ItemCount;
import com.example.ecommerceapp.R;
import com.example.ecommerceapp.database.entities.Item;
import com.google.android.material.button.MaterialButton;

import java.util.Map;

public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.ViewHolder>{
    private CartItemModel[] allItemsCart;
    private SharedPreferences sharedPreferences;
    public CartItemAdapter(CartItemModel[] allItemsCart, SharedPreferences sharedPreferences) {
        this.allItemsCart = allItemsCart;
        this.sharedPreferences = sharedPreferences;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView itemNameTextView;
        private TextView itemPriceTextView;
        private TextView itemQuantityTextView;
        private MaterialButton addItem;
        private MaterialButton removeItem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemNameTextView = itemView.findViewById(R.id.cart_item_name);
            itemPriceTextView = itemView.findViewById(R.id.cart_item_price);
            itemQuantityTextView = itemView.findViewById(R.id.cart_item_quantity);
            addItem = itemView.findViewById(R.id.inc_cart_item);
            removeItem = itemView.findViewById(R.id.dec_cart_item);
        }
    }
    @NonNull
    @Override
    public CartItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.cartitemview1, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CartItemAdapter.ViewHolder holder, int position) {
        final CartItemModel cartItemModel = allItemsCart[position];
        holder.itemNameTextView.setText(cartItemModel.getName());
        holder.itemPriceTextView.setText(String.valueOf(cartItemModel.getPrice()));
        holder.itemQuantityTextView.setText(String.valueOf(cartItemModel.getQuantity()));

        holder.addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartItemModel.setQuantity(cartItemModel.getQuantity() + 1);
                holder.itemQuantityTextView.setText(String.valueOf(cartItemModel.getQuantity()));
                int count= sharedPreferences.getInt(String.valueOf(cartItemModel.getId()),0 );
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt(String.valueOf(cartItemModel.getId()),count - 1);
                editor.commit();
            }
        });
        holder.removeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((cartItemModel.getQuantity() - 1) > 0){
                    cartItemModel.setQuantity(cartItemModel.getQuantity() - 1);
                    holder.itemQuantityTextView.setText(String.valueOf(cartItemModel.getQuantity()));
                    int count= sharedPreferences.getInt(String.valueOf(cartItemModel.getId()),0 );
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt(String.valueOf(cartItemModel.getId()),count - 1);
                    editor.commit();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return allItemsCart.length;
    }
}
