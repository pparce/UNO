package com.example.myapplication.activitys;

import android.os.Bundle;

import com.example.myapplication.R;
import com.example.myapplication.adapters.AdapterCategorias;
import com.example.myapplication.modelos.ModeloCategoria;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class Categorias extends AppCompatActivity {
    Menu menu;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    AdapterCategorias adapterCategorias;

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

        layoutManager = new LinearLayoutManager(Categorias.this);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(layoutManager);
        adapterCategorias = new AdapterCategorias(Categorias.this, getListaCategorias());
        recyclerView.setAdapter(adapterCategorias);

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
            modelo.setTitulo(aux[i]);
            listaAux.add(modelo);
        }
        return listaAux;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.principal, menu);
        this.menu = menu;
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
}
