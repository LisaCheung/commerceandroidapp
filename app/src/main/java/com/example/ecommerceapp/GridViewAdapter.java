package com.example.ecommerceapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ecommerceapp.database.entities.Item;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class GridViewAdapter extends ArrayAdapter<Item> {
    private class InternetImage extends AsyncTask<String, Void, Bitmap> {
        ImageView imageView;
        public InternetImage(ImageView imageView) {
            this.imageView=imageView;
        }
        protected Bitmap doInBackground(String... urls) {
            String imageURL=urls[0];
            Bitmap bimage=null;
            try {
                InputStream in=new java.net.URL(imageURL).openStream();
                bimage=BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error Message", e.getMessage());
                e.printStackTrace();
            }
            return bimage;
        }
        protected void onPostExecute(Bitmap result) {
            imageView.setImageBitmap(result);
        }
    }
    public GridViewAdapter(@NonNull Context context, ArrayList<Item> itemsList) {
        super(context,0, itemsList);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listitemView = convertView;
        if (listitemView == null){
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.gridview_item2,parent,false);

        }
        Item item = getItem(position);
        TextView itemName = listitemView.findViewById(R.id.gridview_item_textview);
        itemName.setText(item.getName());
        TextView itemDescription = listitemView.findViewById(R.id.gridview_item_description);
        TextView itemPrice = listitemView.findViewById(R.id.gridview_item_price);
        itemDescription.setText(item.getDescription());
        itemPrice.setText(String.valueOf(item.getPrice()));
        //img -- setImageResource
        if(item.getItemImage() != null){
            Log.i("ImgURL", item.getItemImage());
            new InternetImage( listitemView.findViewById(R.id.gridview_item_image)).execute(item.getItemImage());
        }
        else{
            new InternetImage( listitemView.findViewById(R.id.gridview_item_image)).execute("https://cdn.cutithai.com/wp-content/uploads/furniture-elegant-small-writing-desk-home_1234483.jpg");
        }
        return listitemView;

    }
}
