package cu.uno.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cu.uno.R;
import cu.uno.adapters.AdapterOpiniones;
import cu.uno.modelos.ModeloOpinion;
import cu.uno.utiles.callbaks.onClickCallBack;


public class FragmentListadoOpiniones extends Fragment implements onClickCallBack {
    View view;
    Context context;
    RecyclerView recycler;
    RecyclerView.LayoutManager layoutManager;
    AdapterOpiniones adapter;
    List<ModeloOpinion> listaOpiniones;
    public static onClickCallBack onClickCallBack;
    LinearLayout emptyScreen;

    public FragmentListadoOpiniones() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_opiniones, container, false);
        FragmentListadoOpiniones.onClickCallBack = this;
        initView();
        return view;
    }

    private void initView() {
        emptyScreen = view.findViewById(R.id.emptyscreen);
        listaOpiniones = new ArrayList<>();
        recycler = view.findViewById(R.id.recyclerview);
        layoutManager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
        adapter = new AdapterOpiniones(context, listaOpiniones);
        /*SpacesItemDecorationEventos itemDecorationEventos = new SpacesItemDecorationEventos(15, 15);
        recycler.addItemDecoration(itemDecorationEventos);*/
        recycler.setLayoutManager(layoutManager);
        recycler.setAdapter(adapter);
        validarLista();
    }

    private void validarLista() {
        if (listaOpiniones.isEmpty()) {
            emptyScreen.setVisibility(View.VISIBLE);
        } else {
            emptyScreen.setVisibility(View.GONE);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }


    @Override
    public void onCLikCallBack(ModeloOpinion lista) {
        listaOpiniones.add(lista);
        adapter.notifyItemInserted(listaOpiniones.size() - 1);
        validarLista();
    }
}
