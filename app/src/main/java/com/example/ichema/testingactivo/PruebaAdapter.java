package com.example.ichema.testingactivo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ichema on 8/16/17.
 */

public class PruebaAdapter extends RecyclerView.Adapter<PruebaAdapter.PruebaViewHolder> {
    private List<Prueba> items;
    private Context context;

    public static class PruebaViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public View view;
        public TextView nombre;

        public PruebaViewHolder(View v) {
            super(v);
            nombre = (TextView) v.findViewById(R.id.nombre);

            view = v;
            view.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    // item clicked
                    System.out.println("GG");
                }
            });
        }
    }

    public PruebaAdapter(List<Prueba> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public PruebaViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.prueba_card, viewGroup, false);
        return new PruebaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PruebaViewHolder viewHolder, int i) {
        viewHolder.nombre.setText(items.get(i).getNombre());
    }
}
