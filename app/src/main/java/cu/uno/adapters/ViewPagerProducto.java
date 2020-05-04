package cu.uno.adapters;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import cu.uno.fragments.FragmentProductoRelacionados;
import cu.uno.fragments.FragmentListadoOpiniones;
import cu.uno.fragments.FragmentProductoInformacion;


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
                fragment = new FragmentProductoInformacion();
                break;
            case 1:
                fragment = new FragmentListadoOpiniones();
                break;
            case 2:
                fragment = new FragmentProductoRelacionados();
                break;
        }

        return fragment;

    }

    @Override
    public int getCount() {
        return tabCount;
    }
}

