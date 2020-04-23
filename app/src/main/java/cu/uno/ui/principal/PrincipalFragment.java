package cu.uno.ui.principal;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import cu.uno.R;
import cu.uno.activitys.Principal;
import cu.uno.adapters.ViewPagerPrincipal;
import cu.uno.utiles.App;

public class PrincipalFragment extends Fragment {

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
        for (int i = 0; i < App.LISTA_CATEGORIAS.size(); i++) {
            tab.addTab(tab.newTab().setText(App.LISTA_CATEGORIAS.get(i).getNombre()));
        }
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
