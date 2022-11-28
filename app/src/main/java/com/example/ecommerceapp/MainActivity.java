package com.example.ecommerceapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

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
}