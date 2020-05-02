package cu.uno.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cu.uno.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;


public class FragmentModalProducto extends BottomSheetDialogFragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    View view;
    Context context;
    private View.OnClickListener listener;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public FragmentModalProducto() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_producto_modal, container, false);

        initView();
        return view;
    }
    private void initView(){
        view.findViewById(R.id.action_agregar_favorito).setOnClickListener(this);
        view.findViewById(R.id.action_descargar_imagen).setOnClickListener(this);
        view.findViewById(R.id.action_quitar).setOnClickListener(this);
        view.findViewById(R.id.action_detalles).setOnClickListener(this);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if (listener != null)
            listener.onClick(v);
    }
}
