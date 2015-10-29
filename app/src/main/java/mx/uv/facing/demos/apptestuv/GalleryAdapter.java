package mx.uv.facing.demos.apptestuv;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder> {
    ArrayList<String> datos;

    public GalleryAdapter (ArrayList<String> datos) {
        this.datos= datos;
    }

    @Override
    public GalleryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listado_item, parent, false);
        return new GalleryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GalleryViewHolder holder, int position) {
        holder.tvTitulo.setText (this.datos.get (position));
    }

    @Override
    public int getItemCount() {
        return this.datos.size();
    }

    class GalleryViewHolder extends RecyclerView.ViewHolder {
       public TextView tvTitulo;

        public GalleryViewHolder(View itemView) {
            super(itemView);
            tvTitulo = (TextView) itemView.findViewById (R.id.tvNombre);
        }

    }
}
