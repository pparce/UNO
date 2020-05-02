package cu.uno.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import cu.uno.R;
import cu.uno.adapters.AdapterPrincipalSiguiendo;
import cu.uno.modelos.ModeloNotas;
import cu.uno.utiles.SpacesItemDecorationEventos;

import java.util.ArrayList;
import java.util.List;


public class FragmentPrincipalSiguiendo extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    View view;
    Context context;
    RecyclerView recycler;
    RecyclerView.LayoutManager layoutManager;
    AdapterPrincipalSiguiendo adapter;




    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public FragmentPrincipalSiguiendo() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_listado_mosaico, container, false);

        initView();
        return view;
    }
    private void initView() {
        recycler = (RecyclerView) view.findViewById(R.id.recyclerview);
        layoutManager = new LinearLayoutManager(context);
        adapter = new AdapterPrincipalSiguiendo(context , getListaNotas());
        SpacesItemDecorationEventos itemDecorationEventos = new SpacesItemDecorationEventos(25,25);
        recycler.addItemDecoration(itemDecorationEventos);
        recycler.setLayoutManager(layoutManager);
        recycler.setAdapter(adapter);
    }

    private List<ModeloNotas> getListaNotas(){
        List<ModeloNotas> lista = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            ModeloNotas modelo = new ModeloNotas();
            modelo.setDescripcion(i + "Esto es una prueba para simular una descripcion de producto que necesito para ver como se ve");
            lista.add(modelo);
        }
        return lista;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }



}
