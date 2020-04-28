package cu.uno.ui.inicio;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import cu.uno.R;
import cu.uno.activitys.Principal;
import cu.uno.adapters.ViewPagerPrincipal;

public class InicioFragment extends Fragment {

    View view;
    ViewPager pager;
    ViewPagerPrincipal pagerAdaptor;
    TabLayout tab;
    Context context;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_principal, container, false);
        initView();
        return view;
    }

    private void initView() {
        tab = Principal.tab;
        pager = view.findViewById(R.id.viewpager);
        pagerAdaptor = new ViewPagerPrincipal(context, getFragmentManager(), tab.getTabCount());
        pager.setAdapter(pagerAdaptor);
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab));
        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab t) {
                Log.e("................", "onTabSelected: " + t.getPosition());
                pager.setCurrentItem(t.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }
}
