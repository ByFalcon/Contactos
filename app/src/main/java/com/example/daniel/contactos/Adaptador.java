package com.example.daniel.contactos;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class Adaptador extends RecyclerView.Adapter<Adaptador.ViewHolder>{

    private LayoutInflater inflater;
    private List<Contacto> data;
    private Context context;



    public Adaptador(Context context, List<Contacto> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onBindViewHolder(Adaptador.ViewHolder holder, final int position) {
        final Contacto item = data.get(position);
        holder.id = data.get(position).getId();
        holder.tvTitulo.setText(item.getNombre());
        holder.tvTexto.setText(item.getTelefono());
    }

    @Override
    public Adaptador.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(inflater == null) {
            inflater = LayoutInflater.from(parent.getContext());
        }
        View v = inflater.inflate(R.layout.item, null, false);
        return new ViewHolder(v);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        long id = 0;
        public TextView tvTitulo;
        public TextView tvTexto;
        public Button btEliminar, btEditar;

        public ViewHolder(View v) {
            super(v);
            tvTitulo = v.findViewById(R.id.textView);
            tvTexto = v.findViewById(R.id.textView2);
            btEliminar = v.findViewById(R.id.btEliminar);
            btEditar = v.findViewById(R.id.btEditar);
            btEliminar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    borrar(id);
                }
            });
        }
    }
    private boolean borrar(long id) {
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getId() == id) {
                data.remove(i);
                notifyItemRemoved(i);
                notifyItemRangeChanged(i, data.size());
                return true;
            }
        }
        return false;
    }
}
