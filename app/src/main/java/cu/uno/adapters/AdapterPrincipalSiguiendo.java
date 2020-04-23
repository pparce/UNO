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
import cu.uno.modelos.ModeloNotas;
import java.util.List;

import cu.uno.R;

/**
 * Created by Suleiman on 26-07-2015.
 */
public class AdapterPrincipalSiguiendo extends RecyclerView.Adapter<AdapterPrincipalSiguiendo.MasonryView> implements View.OnClickListener, View.OnLongClickListener
{

    private Context context;
    List<ModeloNotas> list;
    private View.OnClickListener listener;
    private View.OnLongClickListener onLong;
    private static int TYPE_PUBLICACION = 1;
    private static int TYPE_PRODUCT = 2;

    View layoutView;

    public AdapterPrincipalSiguiendo(Context context, List<ModeloNotas> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getItemViewType(int position) {

        if ( (position & 1) == 0 )
            return TYPE_PUBLICACION;
        else
            return TYPE_PRODUCT;

    }

    @Override
    public MasonryView onCreateViewHolder(ViewGroup parent, int viewType) {


        if (viewType == 1) {
            layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cv_publicacion, parent, false);
        } else {
            layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cv_publicacion, parent, false);
        }
        MasonryView masonryView = new MasonryView(layoutView);
        return masonryView;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(MasonryView holder, int position) {

        holder.descripcion.setText(list.get(position).getDescripcion());

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
