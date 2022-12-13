package com.example.ecommerceapp;

import static android.content.ContentValues.TAG;

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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MyItemsGridView extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;
    private Toolbar toolbar;

    private GridView gridView;
    private ItemsDB itemsDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_items_grid_view);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerLayout = findViewById(R.id.myitems_drawerlayout);
        navigationView = findViewById(R.id.myitems_navView);
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
                    case R.id.myprofile_id:
                        i = new Intent(getApplicationContext(), UserProfileActivity.class);
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
        }); toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerLayout = findViewById(R.id.myitems_drawerlayout);
        navigationView = findViewById(R.id.myitems_navView);
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
                        i = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(i);
                        return true;

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

        gridView = findViewById(R.id.gridview_myitems);
        //TODO my items db
        itemsDatabase = Room.databaseBuilder(getApplicationContext(),
                ItemsDB.class, "itemsDb").allowMainThreadQueries().build();
        ItemsDAO itemsDAO = itemsDatabase.itemsDAO();
        List<Item> allItemsListings = itemsDAO.getAll();
        ArrayList<Item> itemListings = new ArrayList<>();
        for(Item i: allItemsListings){
            itemListings.add(i);
        }
        //TODO display user items only
        FirebaseFirestore.getInstance().collection("UsersListings").document(FirebaseAuth.getInstance().getCurrentUser().getEmail().split("[@]", 0)[0]).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.i("user_items_listings", document.getData().toString());
                        Set<String> keys = document.getData().keySet();
                        ArrayList<Item> allUserItems = new ArrayList<>();
                        for(String k:keys){
//                            int itemIndex = Integer.parseInt(k);
                            Item item3 = new Item();
                            //TODO check null
                            Map<String, Object> mp = (Map<String, Object>) document.getData().get(k);
                            double itemPrice = (double) mp.get("price");
                            String itemName = (String) mp.get("name");
                            long itemId = (long) mp.get("id");
                            item3.setId((int)itemId);
                            item3.setName(itemName);
                            item3.setPrice(itemPrice);
                            if(mp.get("description") != null){
                                String itemDescription = (String) mp.get("description");
                                item3.setDescription(itemDescription);
                            }
                            if(mp.get("category") != null){
                                item3.setCategory((String) mp.get("category"));
                            }
                            if(mp.get("itemImage") != null){
                                item3.setItemImage((String) mp.get("itemImage"));
                            }
                            Log.i("user_items_listings_mp", item3.toString());
                            allUserItems.add(item3);
                        }
                        GridViewAdapter gridViewAdapter = new GridViewAdapter(MyItemsGridView.this,allUserItems);
                        gridView.setAdapter(gridViewAdapter);
                        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                Intent i1 = new Intent(getApplicationContext(), MyItemView.class);
                                int itemIndex = allUserItems.get(i).getId();
                                i1.putExtra("itemIndex", itemIndex);
                                startActivity(i1);
                            }
                        });

//                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
//                        Map<String, String> mp =(Map<String, String>) document.getData().get("user_info");
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
//        GridViewAdapter gridViewAdapter = new GridViewAdapter(this,itemListings);
//        gridView.setAdapter(gridViewAdapter);
//        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Intent i1 = new Intent(getApplicationContext(), MyItemView.class);
//                int itemIndex = itemListings.get(i).getId();
//                i1.putExtra("itemIndex", itemIndex);
//                startActivity(i1);
//
//            }
//        });


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        drawerLayout.openDrawer(GravityCompat.START);
        return super.onOptionsItemSelected(item);
    }
}