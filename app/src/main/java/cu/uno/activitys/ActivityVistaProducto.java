package cu.uno.activitys;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import cu.uno.adapters.ViewPagerProducto;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import cu.uno.R;
import cu.uno.fragments.FragmentListadoOpiniones;
import cu.uno.modelos.ModeloOpinion;
import cu.uno.utiles.ScrollFeedbackRecyclerView;

public class ActivityVistaProducto extends AppCompatActivity implements ScrollFeedbackRecyclerView.Callbacks {

    TabLayout tab;
    ViewPager pager;
    ViewPagerProducto pagerAdaptor;
    private AppBarLayout mAppBarLayout;
    private Toolbar toolbar;
    FloatingActionButton fab;
    int auxCont = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_producto);

        initView();
    }
    private void initView(){
        initFAB();
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
                if (tab.getPosition() == 1) {
                    fab.animate().scaleX(1).setDuration(100).start();
                    fab.animate().scaleY(1).setDuration(100).start();
                } else {
                    fab.animate().scaleX(0).setDuration(100).start();
                    fab.animate().scaleY(0).setDuration(100).start();
                }
                /*if (tab.getPosition() == 0) {
                    mAppBarLayout.setExpanded(true, true);
                    mAppBarLayout.setVisibility(View.VISIBLE);
                } else {
                    mAppBarLayout.setExpanded(false, true);
                    mAppBarLayout.setVisibility(View.VISIBLE);
                }*/
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void initFAB() {
        fab = findViewById(R.id.fab);
        fab.animate().scaleX(0).setDuration(100).start();
        fab.animate().scaleY(0).setDuration(100).start();
        fab.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(ActivityVistaProducto.this);
                builder.setCancelable(true);
                builder.setTitle(getString(R.string.title_activity_add_opinio));
                final View view = getLayoutInflater().inflate(R.layout.dialog_add_opinion, null);

                builder.setView(view);
                builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String mensaje = ((TextView) view.findViewById(R.id.mensaje)).getText().toString();
                        float evaluacion = ((RatingBar) view.findViewById(R.id.ratingBar)).getRating();
                        ModeloOpinion modeloOpinion = new ModeloOpinion();
                        modeloOpinion.setUsuario("peter");
                        modeloOpinion.setMensaje(mensaje);
                        modeloOpinion.setEvaluacion(evaluacion);
                        FragmentListadoOpiniones.onClickCallBack.onCLikCallBack(modeloOpinion);
                    }
                });
                builder.setNegativeButton("cancelar", null);
                builder.create().show();
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
