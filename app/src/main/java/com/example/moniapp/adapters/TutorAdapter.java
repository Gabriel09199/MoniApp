package com.example.moniapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.moniapp.R;
import com.example.moniapp.mundo.Tutor;

import java.util.ArrayList;

public class TutorAdapter extends RecyclerView.Adapter<TutorAdapter.TutorHolder>
{
    private ArrayList<Tutor> tutores;

    //Se ejecuta al añadirse nuevo item
    //Crea una vista TutorHolder y la conecta con los recursos definido en item_tutor_disponible.xml
    @NonNull
    @Override
    public TutorHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_tutor_disponible, viewGroup, false);
        return new TutorHolder(itemView);
    }

    //Inyecta la informacion de los elementos en cada una de las vistas creadas
    @Override
    public void onBindViewHolder(@NonNull TutorHolder tutorHolder, int i)
    {
        tutorHolder.nombreTutor.setText(tutores.get(i).getNombrePersonal());
        ArrayList<String> listaHorarios = new ArrayList<>();

        for(int j = 0; j < tutores.get(i).getHorarios().size(); j++)
        {
            listaHorarios.add(tutores.get(i).getHorarios().get(j).toString());
        }

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(tutorHolder.horarios.getContext(), android.R.layout.simple_spinner_item, listaHorarios);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tutorHolder.horarios.setAdapter(spinnerArrayAdapter);
    }

    @Override
    public int getItemCount()
    {
        return tutores.size();
    }

    public void setTutores(ArrayList<Tutor> tutores)
    {
        this.tutores = tutores;
    }

    //Esta clase representa cada elemento de la coleccion del RecyclerView, item
    class TutorHolder extends RecyclerView.ViewHolder
    {
        private TextView nombreTutor;
        private Spinner horarios;

        public TutorHolder(@NonNull View itemView)
        {
            super(itemView);
            nombreTutor = itemView.findViewById(R.id.txtNombreTutor);
            horarios = itemView.findViewById(R.id.spinnerHorarios);
        }
    }
}