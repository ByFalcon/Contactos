package com.example.daniel.contactos;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class Adaptador extends RecyclerView.Adapter<Adaptador.ViewHolder>{

    private LayoutInflater inflater;
    private List<Contacto> data;

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvTitulo;
        public TextView tvTexto;

        public ViewHolder(View v) {
            super(v);
            tvTitulo = v.findViewById(R.id.textView);
            tvTexto = v.findViewById(R.id.textView2);
        }
    }

    public Adaptador(List<Contacto> data) {
        this.data = data;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onBindViewHolder(Adaptador.ViewHolder holder, final int position) {
        final Contacto item = data.get(position);
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
}
