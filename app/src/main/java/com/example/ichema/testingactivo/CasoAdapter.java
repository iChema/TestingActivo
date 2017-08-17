package com.example.ichema.testingactivo;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.sql.SQLOutput;
import java.util.List;

public class CasoAdapter extends RecyclerView.Adapter<CasoAdapter.CasoViewHolder> {
    private List<Caso> items;
    private Context context;

    public static class CasoViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public View view;
        public ImageView imagen;
        public TextView nombre;
        public TextView descripcion;

        public CasoViewHolder(View v) {
            super(v);
            imagen = (ImageView) v.findViewById(R.id.imagen);
            nombre = (TextView) v.findViewById(R.id.nombre);
            descripcion = (TextView) v.findViewById(R.id.descripcion);

        }
    }

    public CasoAdapter(List<Caso> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public CasoViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.caso_card, viewGroup, false);
        return new CasoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final CasoViewHolder viewHolder, int i) {
        viewHolder.nombre.setText(items.get(i).getNombre());
        viewHolder.descripcion.setText(items.get(i).getDescripcion());
        /*
        viewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, CasoActivity.class);
                Gson gson = new Gson();
                i.putExtra("caso", gson.toJson(items.get(viewHolder.getAdapterPosition())));
                context.startActivity(i);

            }
        });
        */

    }
}
