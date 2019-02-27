package com.example.moniapp.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import com.example.moniapp.R;
import com.example.moniapp.mundo.Asignatura;
import com.example.moniapp.mundo.Horario;

import java.util.ArrayList;

public class HorarioAdapter extends RecyclerView.Adapter<HorarioAdapter.HorarioHolder>
{
    private ArrayList<Horario> horarios;


    @NonNull
    @Override
    public HorarioHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_horario_tutor, viewGroup, false);
        return new HorarioAdapter.HorarioHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HorarioAdapter.HorarioHolder horarioHolder, int i)
    {
        horarioHolder.horario.setText(horarios.get(i).toString());
    }

    @Override
    public int getItemCount()
    {
        return horarios.size();
    }

    public void setHorarios(ArrayList<Horario> horarios)
    {
        this.horarios = horarios;
    }

    class HorarioHolder extends RecyclerView.ViewHolder
    {
        private TextView horario;
        private Switch disponible;

        public HorarioHolder(@NonNull View itemView)
        {
            super(itemView);
            horario = itemView.findViewById(R.id.txtHorario);
            disponible = itemView.findViewById(R.id.switchDisponible);




        }
    }

}
