package cu.uno.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cu.uno.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;


public class FragmentModalProducto extends BottomSheetDialogFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    View view;
    Context context;




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

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }



}
