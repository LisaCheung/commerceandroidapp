package com.example.ecommerceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import com.example.ecommerceapp.database.entities.Item;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;
    private Toolbar toolbar;
private GridView gridView;
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater menuInflater = getMenuInflater();
//        menuInflater.inflate(R.menu.menu1, menu);
//        return true;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabLayout tabLayout=findViewById(R.id.tabLayout);
        TabItem servicesTab = findViewById(R.id.servicesTab);
        TabItem itemsTab = findViewById(R.id.itemsTab);
        ViewPager viewPager = findViewById(R.id.viewPager);
        PagerAdapter1 pagerAdapter = new PagerAdapter1(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerLayout = findViewById(R.id.drawerlayout);
        navigationView = findViewById(R.id.navView);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                Class fragmentClass;
                Intent i;
                switch (item.getItemId())
                {
                    case R.id.home_id:
                        fragmentClass = ItemsFragment.class;
                        break;

                    case R.id.buy_items_id:
                        fragmentClass = ItemsFragment.class;
                        break;

                    case R.id.buy_services_id:
                        fragmentClass = ServicesFragment.class;
                        break;
                    case R.id.sell_items_id:
                        fragmentClass = SellItemsFragment.class;
                        break;
                    case R.id.item_categories_id:
                        i = new Intent(getApplicationContext(), ItemCategories.class);
                      startActivity(i);
                      return true;
                    case R.id.items_listings:
                        i = new Intent(getApplicationContext(), ItemsListings.class);
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
                return true;
            }
        });


//        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
////                Toast.makeText(getApplicationContext(), tab.getText() + "selected", Toast.LENGTH_LONG).show();
//
//                if(tab.getText().equals("Items")){
//                    Intent i = new Intent(getApplicationContext(), ItemsActivity.class);
//                    startActivity(i);
//                }
//                else if(tab.getText().equals("Services")){
//                    Intent i = new Intent(getApplicationContext(), ServicesActivity.class);
//                    startActivity(i);
//                }
//
//
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}