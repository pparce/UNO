package cu.uno.adapters;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import cu.uno.fragments.FragmentPrincipalInicio;


public class ViewPagerSiguiendo extends FragmentStatePagerAdapter {

    int tabCount;
    Context context;

    public ViewPagerSiguiendo(Context context, FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new FragmentPrincipalInicio();
        Bundle bundle = new Bundle();
        bundle.putInt("POSICION", position);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}

