package cu.uno.ui.siguiendo;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import cu.uno.R;
import cu.uno.activitys.Principal;
import cu.uno.adapters.ViewPagerPrincipal;
import cu.uno.utiles.App;

public class SiguiendoFragment extends Fragment {

    View view;
    ViewPager pager;
    ViewPagerPrincipal pagerAdaptor;
    TabLayout tab;
    Context context;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_siguiendo, container, false);
        initView();
        return view;
    }

    private void initView() {
        tab = Principal.tab;

        tab.addTab(tab.newTab().setText("Fabrica de Arte"));
        tab.addTab(tab.newTab().setText("Plomeros en Cuba"));
        tab.addTab(tab.newTab().setText("Etecsa"));

        pager = view.findViewById(R.id.viewpager);
        pagerAdaptor = new ViewPagerPrincipal(context, getFragmentManager(), tab.getTabCount());
        pager.setAdapter(pagerAdaptor);
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab));
        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

}
