package cu.uno.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.MenuItem;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import com.github.clans.fab.FloatingActionMenu;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import java.util.Timer;
import java.util.TimerTask;

import cu.uno.R;
import cu.uno.utiles.ScrollFeedbackRecyclerView;

public class ActivityPrincipal extends AppCompatActivity implements ScrollFeedbackRecyclerView.Callbacks{

    public static TabLayout tab;
    private AppBarConfiguration mAppBarConfiguration;
    AppBarLayout appBarLayout;
    Context context = ActivityPrincipal.this;
    DrawerLayout drawer;
    ImageView avatar;
    int atras = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        initView();
    }

    private void initView() {
        setupFAB();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        tab = findViewById(R.id.tab);
        appBarLayout = findViewById(R.id.app_bar);
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_inicio, R.id.nav_negocio, R.id.nav_favoritos)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                int id = destination.getId();
                if (id == R.id.nav_inicio) {
                    appBarLayout.setElevation(0);
                } else if (id == R.id.nav_negocio) {
                    appBarLayout.setElevation(10);
                }
            }
        });

        avatar = (navigationView.getHeaderView(0)).findViewById(R.id.avatar);
        avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.closeDrawers();
                startActivity(new Intent(ActivityPrincipal.this, ActivityUsuario.class));
            }
        });
    }

    private void setupFAB (){
        final FloatingActionMenu floatingActionMenu = findViewById(R.id.menu);
        floatingActionMenu.setClosedOnTouchOutside(true);
        findViewById(R.id.menu_publicacion).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, ActivityAddProducto.class));
                floatingActionMenu.close(true);

            }
        });

        findViewById(R.id.menu_servicio).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        findViewById(R.id.menu_solicitud).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, ActivityAddSolicitud.class));
                floatingActionMenu.close(true);
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
            startActivity(new Intent(ActivityPrincipal.this, ActivityBuscar.class));
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
                Toast.makeText(ActivityPrincipal.this, "Presione una vez mas para cerrar ", Toast.LENGTH_SHORT).show();

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
    public boolean isAppBarCollapsed() {
        return false;
    }

    @Override
    public void setExpanded(boolean expanded) {

    }
}
