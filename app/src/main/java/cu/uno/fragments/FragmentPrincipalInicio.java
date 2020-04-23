package cu.uno.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import cu.uno.R;
import cu.uno.activitys.VistaNegocio;
import cu.uno.activitys.VistaProducto;
import cu.uno.adapters.AdapterPrincipalInicio;
import cu.uno.modelos.ModeloNotas;
import cu.uno.utiles.SpacesItemDecorationEventos;

import java.util.ArrayList;
import java.util.List;


public class FragmentPrincipalInicio extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    View view;
    Context context;
    RecyclerView recycler;
    RecyclerView.LayoutManager layoutManager;
    AdapterPrincipalInicio adapter;
    boolean ENCABEZADO =false;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public FragmentPrincipalInicio() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_principal_inicio, container, false);
        initView();
        return view;
    }

    private void initView() {

        Bundle bundle = getArguments();
        if (bundle != null) {
            ENCABEZADO = bundle.getBoolean("ENCABEZADO");
        }

        recycler = (RecyclerView) view.findViewById(R.id.recyclerview);
        layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        adapter = new AdapterPrincipalInicio(context, getListaNotas(), ENCABEZADO);
        SpacesItemDecorationEventos itemDecorationEventos = new SpacesItemDecorationEventos(25, 25);
        recycler.addItemDecoration(itemDecorationEventos);
        recycler.setLayoutManager(layoutManager);
        recycler.setAdapter(adapter);
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (view.getClass() == CardView.class) {
                    startActivity(new Intent(context, VistaProducto.class));
                } else {
                    startActivity(new Intent(context, VistaNegocio.class));
                }
            }
        });


        adapter.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                FragmentModalProducto modalProducto = new FragmentModalProducto();
                modalProducto.show(getActivity().getSupportFragmentManager(), modalProducto.getTag());

                return false;
            }
        });
    }

    private List<ModeloNotas> getListaNotas() {
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
