package com.example.myapplication.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.example.myapplication.R;


public class FragmentProductoInformacion extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    View view;
    Context context;
    ImageView avatarNegocio;
    boolean ENCABEZADO = false;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public FragmentProductoInformacion() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_producto_informacion, container, false);

        initView();
        return view;
    }

    private void initView() {
        avatarNegocio = (ImageView) view.findViewById(R.id.avatar_negocio);
        avatarNegocio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* startActivity(new Intent(context, VistaNegocio.class));*/
            }
        });
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }


}
