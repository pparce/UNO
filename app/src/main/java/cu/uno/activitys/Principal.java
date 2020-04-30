package cu.uno.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import com.github.clans.fab.FloatingActionMenu;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
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
import java.util.Timer;
import java.util.TimerTask;

import cu.uno.R;

public class Principal extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    public static TabLayout tab;
    private AppBarConfiguration mAppBarConfiguration;
    Context context = Principal.this;
    DrawerLayout drawer;
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
        tab = findViewById(R.id.tab);
        tab.addTab(tab.newTab().setText("Pincipal"));
        tab.addTab(tab.newTab().setText("Siguiendo"));
        tab.addTab(tab.newTab().setText("Destacado"));


        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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

    private void setupFAB (){
        final FloatingActionMenu floatingActionMenu = findViewById(R.id.menu);
        floatingActionMenu.setClosedOnTouchOutside(true);
        ImageButton addPublicacion = findViewById(R.id.menu_publicacion);
        addPublicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, AddProducto.class));
                floatingActionMenu.close(true);

            }
        });
        ImageButton addSolicitud = findViewById(R.id.menu_solicitud);
        addSolicitud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, AddSolicitud.class));
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
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        if (id == R.id.nav_favoritos) {
            startActivity(new Intent(context, Favoritos.class));
        }
        Toast.makeText(context, "click", Toast.LENGTH_SHORT).show();
        drawer.closeDrawer(GravityCompat.START);
        return false;
    }
}
