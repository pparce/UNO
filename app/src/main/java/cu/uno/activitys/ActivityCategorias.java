package cu.uno.activitys;

import android.content.Intent;
import android.os.Bundle;

import cu.uno.adapters.AdapterCategorias;
import cu.uno.modelos.ModeloCategoria;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import cu.uno.R;

public class ActivityCategorias extends AppCompatActivity {
    Menu menu;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    AdapterCategorias adapterCategorias;
    List<Integer> listaSeleccionadas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias);
        initView();

    }

    private void initView() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listaSeleccionadas = new ArrayList<>();

        layoutManager = new LinearLayoutManager(ActivityCategorias.this);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(layoutManager);
        adapterCategorias = new AdapterCategorias(ActivityCategorias.this, getListaCategorias());
        recyclerView.setAdapter(adapterCategorias);
        adapterCategorias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = recyclerView.getChildAdapterPosition(v);
                int id = adapterCategorias.getLista().get(position).getId();
                if (v.isSelected()) {
                    v.setSelected(false);
                    listaSeleccionadas.remove(listaSeleccionadas.indexOf(id));
                } else {
                    v.setSelected(true);
                    listaSeleccionadas.add(id);
                }

                if (listaSeleccionadas.size() >= 3) {
                    menu.findItem(R.id.action_done).setVisible(true);
                } else {
                    menu.findItem(R.id.action_done).setVisible(false);
                }
            }
        });

    }

    private List<ModeloCategoria> getListaCategorias() {
        String[] aux = {
                "Hogar",
                "Moda y Belleza",
                "Recreacion",
                "Animales",
                "Electronica",
                "Gastonomia",
                "Educacion",
                "Viajes",
                "Entretenimiento",
                "Deportes",
                "Fotografia",
                "Autos",
                "Servicios"
        };
        List<ModeloCategoria> listaAux = new ArrayList<>();
        for (int i = 0; i < aux.length; i++) {
            ModeloCategoria modelo = new ModeloCategoria();
            modelo.setNombre(aux[i]);
            listaAux.add(modelo);
        }
        return listaAux;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.categorias, menu);
        this.menu = menu;
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
        } else if (id == R.id.action_done) {
            startActivity(new Intent(ActivityCategorias.this, ActivityPrincipal.class));
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
