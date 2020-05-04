package cu.uno.adapters;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cu.uno.R;
import cu.uno.modelos.ModeloNotas;
import cu.uno.modelos.ModeloOpinion;

/**
 * Created by Suleiman on 26-07-2015.
 */
public class AdapterOpiniones extends RecyclerView.Adapter<AdapterOpiniones.MasonryView> implements View.OnClickListener, View.OnLongClickListener {

    private Context context;
    List<ModeloOpinion> list;
    private View.OnClickListener listener;
    private View.OnLongClickListener onLong;
    private static int TYPE_PUBLICACION = 1;
    private static int TYPE_PRODUCT = 2;

    View layoutView;

    public AdapterOpiniones(Context context, List<ModeloOpinion> list) {
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

        layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cv_opinion, parent, false);

        MasonryView masonryView = new MasonryView(layoutView);
        return masonryView;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(MasonryView holder, int position) {
        holder.usuario.setText(list.get(position).getUsuario());
//        holder.avatar.setImageBitmap(list.get(position).getAvatar());
        holder.mensaje.setText(list.get(position).getMensaje());
        holder.evaluacion.setRating(list.get(position).getEvaluacion());
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
        TextView usuario, mensaje;
        RatingBar evaluacion;
        ImageView avatar;

        public MasonryView(View itemView) {
            super(itemView);
                usuario = itemView.findViewById(R.id.usuario);
                avatar = itemView.findViewById(R.id.avatar);
                mensaje = itemView.findViewById(R.id.mensaje);
                evaluacion = itemView.findViewById(R.id.ratingBar);
        }
    }

}
