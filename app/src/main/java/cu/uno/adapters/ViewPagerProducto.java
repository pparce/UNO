package cu.uno.adapters;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import cu.uno.fragments.FragmentPrincipalInicio;
import cu.uno.fragments.FragmentProductoMensajes;


public class ViewPagerProducto extends FragmentStatePagerAdapter {

    int tabCount;
    Context context;

    public ViewPagerProducto(Context context, FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {

        Fragment fragment = new Fragment();

        switch (position) {
            case 0:
                Bundle bundle = new Bundle();
                bundle.putBoolean("ENCABEZADO", true);
                fragment = new FragmentPrincipalInicio();
                fragment.setArguments(bundle);
                break;
            case 1:
                fragment = new FragmentProductoMensajes();
                break;
        }

        return fragment;

    }

    @Override
    public int getCount() {
        return tabCount;
    }
}

