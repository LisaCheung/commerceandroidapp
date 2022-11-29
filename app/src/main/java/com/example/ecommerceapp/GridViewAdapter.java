package com.example.ecommerceapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ecommerceapp.database.entities.Item;

import java.util.ArrayList;

public class GridViewAdapter extends ArrayAdapter<Item> {
    public GridViewAdapter(@NonNull Context context, ArrayList<Item> itemsList) {
        super(context,0, itemsList);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listitemView = convertView;
        if (listitemView == null){
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.gridview_item,parent,false);

        }
        Item item = getItem(position);
        TextView itemName = listitemView.findViewById(R.id.gridview_item_textview);
        itemName.setText(item.getName());
        //img -- setImageResource
        return listitemView;

    }
}
