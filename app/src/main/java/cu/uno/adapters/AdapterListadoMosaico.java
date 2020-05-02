package cu.uno.adapters;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import cu.uno.modelos.ModeloNotas;

import java.util.List;

import cu.uno.R;

/**
 * Created by Suleiman on 26-07-2015.
 */
public class AdapterListadoMosaico extends RecyclerView.Adapter<AdapterListadoMosaico.MasonryView> implements View.OnClickListener, View.OnLongClickListener {

    private Context context;
    List<ModeloNotas> list;
    private View.OnClickListener listener;
    private View.OnLongClickListener onLong;
    private static int VISTA_ENCABEZADO = 1;
    private static int VISTA_IMAGEN = 2;
    private static int VISTA_NOTA = 3;
    boolean ENCABEZADO = false;

    View layoutView;

    public AdapterListadoMosaico(Context context, List<ModeloNotas> list, boolean encabezado) {
        this.context = context;
        this.list = list;
        ENCABEZADO = encabezado;
    }

    @Override
    public int getItemViewType(int position) {

        if (ENCABEZADO) {
            if (position == 0)
                return VISTA_ENCABEZADO;
            else
                return VISTA_IMAGEN;
        } else {
            if ((position & 1) != 0)
                return VISTA_IMAGEN;
            else
                return VISTA_NOTA;
        }


    }

    @Override
    public MasonryView onCreateViewHolder(ViewGroup parent, int viewType) {


        if (viewType == VISTA_ENCABEZADO) {
            layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cv_encabezado, parent, false);
        } else if (viewType == VISTA_IMAGEN) {
            layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cv_imagen, parent, false);
        } else if (viewType == VISTA_NOTA) {
            layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cv_notas, parent, false);
        }
        MasonryView masonryView = new MasonryView(layoutView);
        return masonryView;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(MasonryView holder, int position) {

        if (ENCABEZADO) {
            if (position == 0) {
                StaggeredGridLayoutManager.LayoutParams layoutParams = (StaggeredGridLayoutManager.LayoutParams) holder.itemView.getLayoutParams();
                layoutParams.setFullSpan(true);
                if (layoutView.findViewById(R.id.avatar_negocio) != null) {
                    layoutView.findViewById(R.id.avatar_negocio).setOnClickListener(this);

                }
            } else {
                layoutView.setOnClickListener(this);
                layoutView.setOnLongClickListener(this);
                holder.descripcion.setText(list.get(position).getDescripcion());
            }
        } else {
            holder.descripcion.setText(list.get(position).getDescripcion());
            layoutView.setOnClickListener(this);
            layoutView.setOnLongClickListener(this);
        }


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
        return true;
    }


    class MasonryView extends RecyclerView.ViewHolder {
        TextView descripcion, titulo;
        ImageView portada;

        public MasonryView(View itemView) {
            super(itemView);

            descripcion = (TextView) itemView.findViewById(R.id.descripcion);


        }
    }

}
