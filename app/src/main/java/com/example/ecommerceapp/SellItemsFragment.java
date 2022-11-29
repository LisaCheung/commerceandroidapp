package com.example.ecommerceapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SellItemsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SellItemsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SellItemsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SellItemsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SellItemsFragment newInstance(String param1, String param2) {
        SellItemsFragment fragment = new SellItemsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    private TabLayout tabLayout;
    private TabItem servicesTab;
    private TabItem itemsTab;
    private ViewPager viewPager;
    private FragmentManager fragmentManager;
    private PagerAdapter1 pagerAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

//        TabLayout tabLayout=getView().findViewById(R.id.tabLayout);
//        TabItem servicesTab = getView().findViewById(R.id.servicesTab);
//        TabItem itemsTab = getView().findViewById(R.id.itemsTab);
//        ViewPager viewPager = getView().findViewById(R.id.viewPager);

//        viewPager.setAdapter(pagerAdapter);
//        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        tabLayout=getView().findViewById(R.id.tabLayout);
//         servicesTab = getView().findViewById(R.id.servicesTab);
//         itemsTab = getView().findViewById(R.id.itemsTab);
//         viewPager = getView().findViewById(R.id.viewPager);
//fragmentManager = getActivity().getSupportFragmentManager();

        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_sell_items, container, false);
    }

//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
////        tabLayout=getView().findViewById(R.id.tabLayout);
////         servicesTab = getView().findViewById(R.id.servicesTab);
////         itemsTab = getView().findViewById(R.id.itemsTab);
////         viewPager = getView().findViewById(R.id.viewPager);
////        fragmentManager = getActivity().getSupportFragmentManager();
////        pagerAdapter = new PagerAdapter1(fragmentManager, tabLayout.getTabCount());
//    }
}