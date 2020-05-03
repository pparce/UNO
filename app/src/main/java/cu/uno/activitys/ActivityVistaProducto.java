package cu.uno.activitys;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import cu.uno.adapters.ViewPagerProducto;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

import cu.uno.R;
import cu.uno.utiles.ScrollFeedbackRecyclerView;

public class ActivityVistaProducto extends AppCompatActivity implements ScrollFeedbackRecyclerView.Callbacks {

    TabLayout tab;
    ViewPager pager;
    ViewPagerProducto pagerAdaptor;
    private AppBarLayout mAppBarLayout;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_producto);

        initView();
    }
    private void initView(){
        toolbar =findViewById(R.id.toolbar);
        mAppBarLayout = findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tab = (TabLayout) findViewById(R.id.tab);
        tab.addTab(tab.newTab().setText(getResources().getString(R.string.tab_producto_informacion)));
        tab.addTab(tab.newTab().setText(getResources().getString(R.string.tab_producto_opiniones)));
        tab.addTab(tab.newTab().setText(getResources().getString(R.string.tab_producto_relacionados)));

        pager = (ViewPager) findViewById(R.id.viewpager);
        pagerAdaptor = new ViewPagerProducto(this, getSupportFragmentManager(), tab.getTabCount());
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.vista_producto, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean isAppBarCollapsed() {
        final int appBarVisibleHeight = (int) (mAppBarLayout.getY() + mAppBarLayout.getHeight());
        final int toolbarHeight = toolbar.getHeight();
        return (appBarVisibleHeight == toolbarHeight);
    }

    @Override
    public void setExpanded(boolean expanded) {
        mAppBarLayout.setExpanded(expanded, true);
    }
}
