package com.example.calculation.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.calculation.ui.fragments.DrinksFragment;
import com.example.calculation.ui.fragments.FoodFragment;
import com.example.calculation.ui.fragments.SnaksFragment;

public class MyFragAdapter extends FragmentPagerAdapter {

    private int pageCount ;

    public MyFragAdapter(@NonNull FragmentManager fm, int behavior , int pageCount) {
        super(fm, behavior);
        this.pageCount=pageCount ;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 1:
                return new DrinksFragment();
            case 2:
                return new SnaksFragment();
            default:
                return new FoodFragment();
        }
    }
    @Override
    public int getCount() {
        return pageCount;
    }
}

