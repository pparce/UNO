package cu.uno.activitys;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import cu.uno.R;
import cu.uno.adapters.AdapterAddImagenes;
import cu.uno.utiles.Util;
import cu.uno.utiles.bsimagepicker.BSImagePicker;

public class AddProducto extends AppCompatActivity implements BSImagePicker.OnSingleImageSelectedListener,
        BSImagePicker.OnMultiImageSelectedListener, BSImagePicker.ImageLoaderDelegate, BSImagePicker.OnSelectImageCancelledListener {

    List<Uri> listaImagenes;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    AdapterAddImagenes adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_producto);
        initView();
    }

    private void initView() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        listaImagenes = new ArrayList<>();
        initRecycler();
        findViewById(R.id.add_imagen).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listaImagenes.size() < 5) {
                    BSImagePicker pickerDialog = new BSImagePicker.Builder("com.asksira.imagepickersheetdemo.fileprovider")
                            .setMaximumDisplayingImages(Integer.MAX_VALUE)
                            .isMultiSelect()
                            .setMinimumMultiSelectCount(1)
                            .setMaximumMultiSelectCount(5 - listaImagenes.size())
                            .useFrontCamera()
                            .build();
                    pickerDialog.show(getSupportFragmentManager(), "picker");
                } else {
                    Snackbar.make(v, "solo puede agregar 5 imagenes", Snackbar.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initRecycler() {

        layoutManager = new LinearLayoutManager(AddProducto.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new AdapterAddImagenes(AddProducto.this, listaImagenes);
        recyclerView.setAdapter(adapter);
        adapter.setOnLongClickListener(new View.OnLongClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public boolean onLongClick(View v) {
                final int position = recyclerView.getChildAdapterPosition(v);
                final Uri aux = listaImagenes.get(position);
                listaImagenes.remove(position);
                adapter.notifyItemRemoved(position);
                adapter.notifyItemRangeChanged(position, adapter.getItemCount() - position);
                Snackbar.make(v, "Imagen quitada de la lista", Snackbar.LENGTH_LONG)
                        .setActionTextColor(getColor(R.color.colorPrimary))
                        .setAction("Deshacer", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                listaImagenes.add(position, aux);
                                adapter.notifyItemInserted(position);
                                adapter.notifyItemRangeChanged(position, adapter.getItemCount() - 1);
                            }
                        }).show();
                return false;
            }
        });
        validarLista();
    }

    /*private void initSpinner() {
        Spinner spinner = findViewById(R.id.spinner);
        String[] tiposPublicacion = {
                "Producto",
                "Evento",
                "servicio"
        };
    }*/


    private void validarLista() {
        if (listaImagenes.size() != 0) {
            recyclerView.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_producto, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
        } else if (id == R.id.action_info) {
            Util.showDialogSimple(AddProducto.this,
                    "Producto", "Esta es una descripcion para simular la explicacion de la ventana de Producto");
        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onSingleImageSelected(Uri uri, String tag) {
        listaImagenes.add(uri);
        adapter.notifyItemInserted(listaImagenes.size() - 1);
        recyclerView.scrollToPosition(listaImagenes.size() - 1);
        validarLista();
    }

    @Override
    public void onMultiImageSelected(List<Uri> uriList, String tag) {
        int posInicial = listaImagenes.size() ;
        listaImagenes.addAll(uriList);
        adapter.notifyItemRangeInserted(posInicial, listaImagenes.size() - 1);
        recyclerView.scrollToPosition(listaImagenes.size() - 1);
        validarLista();
    }

    @Override
    public void loadImage(Uri imageUri, ImageView ivImage) {
        Glide.with(AddProducto.this).load(imageUri).into(ivImage);
    }

    @Override
    public void onCancelled(boolean isMultiSelecting, String tag) {
    }
}
