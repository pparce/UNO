package com.example.myapplication.adapters;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.myapplication.fragments.FragmentPrincipalDestacado;
import com.example.myapplication.fragments.FragmentPrincipalInicio;
import com.example.myapplication.fragments.FragmentPrincipalSiguiendo;


public class ViewPagerPrincipal extends FragmentStatePagerAdapter {

    int tabCount;
    Context context;

    public ViewPagerPrincipal(Context context, FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {

        Fragment fragment = new Fragment();

        switch (position){
            case 0:
                fragment = new FragmentPrincipalInicio();
                break;
            case 1:
                fragment = new FragmentPrincipalSiguiendo();
                break;
            case 2:
                fragment = new FragmentPrincipalDestacado();
                break;
        }

        return fragment;

    }

    @Override
    public int getCount() {
        return tabCount;
    }
}

