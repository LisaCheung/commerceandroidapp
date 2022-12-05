package com.example.ecommerceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.room.Room;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.ecommerceapp.database.ItemsDAO;
import com.example.ecommerceapp.database.ItemsDB;
import com.example.ecommerceapp.database.entities.Item;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class ItemsListings extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;
    private Toolbar toolbar;

    private GridView gridView;
    private ItemsDB itemsDatabase;
    private MaterialButton shoppingCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items_listings);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerLayout = findViewById(R.id.items_listings_drawerlayout);
        navigationView = findViewById(R.id.items_listings_navView);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                getSupportActionBar().setTitle(item.getTitle());
                Fragment fragment = null;
                Class fragmentClass;
                Intent i;
                switch (item.getItemId())
                {
                    case R.id.my_items:
                        i = new Intent(getApplicationContext(), MyItemsGridView.class);
                        startActivity(i);
                        return true;
                    case R.id.home_id:
                        fragmentClass = ItemsFragment.class;
                        break;

//                    case R.id.buy_items_id:
//                        fragmentClass = ItemsFragment.class;
//                        break;
//
//                    case R.id.buy_services_id:
//                        fragmentClass = ServicesFragment.class;
//                        break;
//                    case R.id.sell_items_id:
//                        fragmentClass = SellItemsFragment.class;
//                        break;
//                    case R.id.item_categories_id:
//                        i = new Intent(getApplicationContext(), ItemCategories.class);
//                        startActivity(i);
//                        return true;
                    case R.id.items_listings:
                        i = new Intent(getApplicationContext(), ItemsListings.class);
                        startActivity(i);
                        return true;
                    case R.id.add_item:
                        i = new Intent(getApplicationContext(), AddItemUI.class);
                        startActivity(i);
                        return true;
                    default:
                        fragmentClass = ItemsFragment.class;


                }
                try{
                    fragment = (Fragment) fragmentClass.newInstance();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.nav_frame,fragment).commit();
                setTitle(item.getTitle());
                item.setChecked(true);
                drawerLayout.closeDrawers();
                item.setChecked(false);
                return true;
            }
        });



        shoppingCart = findViewById(R.id.shoppingcart);
        shoppingCart.setBackgroundColor(Color.GRAY);
        shoppingCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO : shopping cart fragment & checkout fragment
                Intent i = new Intent(getApplicationContext(), CartActivity.class);
                startActivity(i);
            }
        });
        itemsDatabase = Room.databaseBuilder(getApplicationContext(),
                ItemsDB.class, "itemsDb").allowMainThreadQueries().build();
        ItemsDAO itemsDAO = itemsDatabase.itemsDAO();
        List<Item> allItemsListings = itemsDAO.getAll();

        //TODO allItemsListings for gridview adapter
        //grid view item listings
        gridView = findViewById(R.id.gridview_itemlistings);
        ArrayList<Item> itemListings = new ArrayList<>();
        for(Item i: allItemsListings){
            itemListings.add(i);
        }
//        itemListings.add(new Item("item1", "descr1"));
//        itemListings.add(new Item("item2", "descr2"));
//        itemListings.add(new Item("item3", "descr3"));
        GridViewAdapter gridViewAdapter = new GridViewAdapter(this,itemListings);
        gridView.setAdapter(gridViewAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext() , itemListings.get(i).getName(), Toast.LENGTH_LONG).show();
                Log.i("TAG", itemListings.get(i).getName());
                //redirect to new activity putExtra to send data
//                MaterialButton plusButton = view.findViewById(R.id.plus_icon);
//                plusButton.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Toast.makeText(getApplicationContext(), "+ " + itemListings.get(i).getName(), Toast.LENGTH_LONG).show();
//                    }
//                });
                Intent i1 = new Intent(getApplicationContext(), ItemView1.class);
                int itemIndex = itemListings.get(i).getId();
                i1.putExtra("itemIndex", itemIndex);
                startActivity(i1);

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        drawerLayout.openDrawer(GravityCompat.START);
        return super.onOptionsItemSelected(item);
    }
}