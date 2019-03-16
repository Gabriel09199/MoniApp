package com.example.moniapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import com.example.moniapp.R;
import com.example.moniapp.mundo.Asignatura;
import com.example.moniapp.servicios.ServicioMoniApp;

import java.util.ArrayList;

public class ClaseAdapter extends RecyclerView.Adapter<ClaseAdapter.ClaseHolder>
{
    private ArrayList<Asignatura> clases;

    @NonNull
    @Override
    public ClaseHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_clases_tutor, viewGroup, false);
        return new ClaseHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ClaseHolder claseHolder, int i)
    {
        claseHolder.nombreClase.setText(clases.get(i).getNombre());
    }

    @Override
    public int getItemCount()
    {
        return clases.size();
    }

    public void setClases(ArrayList<Asignatura> clases)
    {
        this.clases = clases;
    }

    class ClaseHolder extends RecyclerView.ViewHolder
    {

        private TextView nombreClase;

        public ClaseHolder(@NonNull View itemView)
        {
            super(itemView);
            nombreClase = itemView.findViewById(R.id.txtNombreClase);
        }
    }
}
