package cu.uno.activitys;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionMenu;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionButton;
import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionHelper;
import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionLayout;
import com.wangjie.rapidfloatingactionbutton.contentimpl.labellist.RFACLabelItem;
import com.wangjie.rapidfloatingactionbutton.contentimpl.labellist.RapidFloatingActionContentLabelList;
import com.wangjie.rapidfloatingactionbutton.util.RFABShape;
import com.wangjie.rapidfloatingactionbutton.util.RFABTextUtil;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import cu.uno.R;

public class Principal extends AppCompatActivity implements RapidFloatingActionContentLabelList.OnRapidFloatingActionContentLabelListListener {

    public static TabLayout tab;
    private AppBarConfiguration mAppBarConfiguration;
    int atras = 0;
    Context context = Principal.this;

    private RapidFloatingActionLayout rfaLayout;
    private RapidFloatingActionButton rfaBtn;
    private RapidFloatingActionHelper rfabHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        initView();
    }

    private void initView() {
//        initFAb();

        final FloatingActionMenu floatingActionButton = findViewById(R.id.menu);
        floatingActionButton.setClosedOnTouchOutside(true);
        ImageButton imageButton = findViewById(R.id.menu_publicacion);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                floatingActionButton.close(true);
            }
        });
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tab = findViewById(R.id.tab);
        tab.addTab(tab.newTab().setText("Pincipal"));
        tab.addTab(tab.newTab().setText("Siguiendo"));
        tab.addTab(tab.newTab().setText("Destacado"));


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_inicio, R.id.nav_negocio)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                int id = destination.getId();
                if (id == R.id.nav_inicio) {
                    tab.setVisibility(View.VISIBLE);
                } else if (id == R.id.nav_negocio) {
                    tab.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_buscar) {
            startActivity(new Intent(Principal.this, Buscar.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (atras == 1) {
                super.onBackPressed();
            }
            if (atras == 0) {
                atras = 1;
                Toast.makeText(Principal.this, "Presione una vez mas para cerrar ", Toast.LENGTH_SHORT).show();

                TimerTask timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        atras = 0;
                    }
                };
                Timer timer = new Timer();
                timer.schedule(timerTask, 2000);
            }
        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void onRFACItemLabelClick(int position, RFACLabelItem item) {
        Toast.makeText(context, "clicked label: " + position, Toast.LENGTH_SHORT).show();
        rfabHelper.toggleContent();
    }

    @Override
    public void onRFACItemIconClick(int position, RFACLabelItem item) {
        Toast.makeText(context, "clicked icon: " + position, Toast.LENGTH_SHORT).show();
        rfabHelper.toggleContent();
    }
}
