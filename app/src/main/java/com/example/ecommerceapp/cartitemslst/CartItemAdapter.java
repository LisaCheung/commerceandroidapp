package com.example.ecommerceapp.cartitemslst;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecommerceapp.R;

public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.ViewHolder>{
    private CartItemModel[] allItemsCart;

    public CartItemAdapter(CartItemModel[] allItemsCart) {
        this.allItemsCart = allItemsCart;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView itemNameTextView;
        private TextView itemPriceTextView;
        private TextView itemQuantityTextView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemNameTextView = itemView.findViewById(R.id.cart_item_name);
            itemPriceTextView = itemView.findViewById(R.id.cart_item_price);
            itemQuantityTextView = itemView.findViewById(R.id.cart_item_quantity);
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
        final CartItemModel recyclerViewModel = allItemsCart[position];
        holder.itemNameTextView.setText(recyclerViewModel.getName());
        holder.itemPriceTextView.setText(String.valueOf(recyclerViewModel.getPrice()));
        holder.itemQuantityTextView.setText(String.valueOf(recyclerViewModel.getQuantity()));
    }

    @Override
    public int getItemCount() {
        return allItemsCart.length;
    }
}
