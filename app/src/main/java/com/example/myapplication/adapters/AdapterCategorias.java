package com.example.myapplication.adapters;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.modelos.ModeloCategoria;

import java.util.List;

/**
 * Created by Suleiman on 26-07-2015.
 */
public class AdapterCategorias extends RecyclerView.Adapter<AdapterCategorias.MasonryView> implements View.OnClickListener, View.OnLongClickListener {

    private Context context;
    List<ModeloCategoria> list;
    private View.OnClickListener listener;
    private View.OnLongClickListener onLong;
    private static int TYPE_PUBLICACION = 1;
    private static int TYPE_PRODUCT = 2;

    View layoutView;

    public AdapterCategorias(Context context, List<ModeloCategoria> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getItemViewType(int position) {

        if ((position & 1) == 0)
            return TYPE_PUBLICACION;
        else
            return TYPE_PRODUCT;

    }

    @Override
    public MasonryView onCreateViewHolder(ViewGroup parent, int viewType) {

        layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cv, parent, false);

        MasonryView masonryView = new MasonryView(layoutView);
        return masonryView;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(MasonryView holder, int position) {

        holder.titulo.setText(list.get(position).getTitulo());

        layoutView.setOnClickListener(this);
        layoutView.setOnLongClickListener(this);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if (listener != null)
            listener.onClick(view);
    }

    public void setOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.onLong = onLongClickListener;
    }

    @Override
    public boolean onLongClick(View view) {
        if (onLong != null)
            onLong.onLongClick(view);
        return false;
    }


    class MasonryView extends RecyclerView.ViewHolder {
        TextView  titulo;

        public MasonryView(View itemView) {
            super(itemView);

            titulo = itemView.findViewById(R.id.titulo);


        }
    }

}