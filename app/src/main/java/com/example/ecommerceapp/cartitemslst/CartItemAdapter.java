package com.example.ecommerceapp.cartitemslst;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecommerceapp.CartActivity;
import com.example.ecommerceapp.ItemCount;
import com.example.ecommerceapp.R;
import com.example.ecommerceapp.database.entities.Item;
import com.google.android.material.button.MaterialButton;

public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.ViewHolder>{
    private CartItemModel[] allItemsCart;

    public CartItemAdapter(CartItemModel[] allItemsCart) {
        this.allItemsCart = allItemsCart;
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
/*
itemCount = new ViewModelProvider(this).get(ItemCount.class);
        LiveData<Integer> itemCount2 = itemCount.getInitialCount();
        itemQuantity.setText(String.valueOf(itemCount2.getValue()));
        itemCount2.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                itemQuantity.setText(String.valueOf(integer.intValue()));
            }
        });

        increaseCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemCount.increaseCount();
            }
        });
 */
    @Override
    public void onBindViewHolder(@NonNull CartItemAdapter.ViewHolder holder, int position) {
        final CartItemModel recyclerViewModel = allItemsCart[position];
        holder.itemNameTextView.setText(recyclerViewModel.getName());
        holder.itemPriceTextView.setText(String.valueOf(recyclerViewModel.getPrice()));
        holder.itemQuantityTextView.setText(String.valueOf(recyclerViewModel.getQuantity()));
        holder.addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        holder.removeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return allItemsCart.length;
    }
}
