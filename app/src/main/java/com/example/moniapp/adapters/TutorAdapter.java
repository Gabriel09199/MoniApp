package com.example.moniapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.moniapp.R;
import com.example.moniapp.mundo.Tutor;
import com.example.moniapp.servicios.ServicioMoniApp;

import java.util.ArrayList;

public class TutorAdapter extends RecyclerView.Adapter<TutorAdapter.TutorHolder>
{
    private ArrayList<Tutor> tutores;
    private String nombreAsignaturaActual;

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

        //Set events
        tutorHolder.setOnClickListener();
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

    public void setAsignaturaActual(String nombreAsignatura) { this.nombreAsignaturaActual = nombreAsignatura; }


    //Esta clase representa cada elemento de la coleccion del RecyclerView, item
    class TutorHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        Context context;
        private TextView nombreTutor;
        private Spinner horarios;
        private Button btnSolicitar;

        public TutorHolder(@NonNull View itemView)
        {
            super(itemView);
            context = itemView.getContext();
            nombreTutor = itemView.findViewById(R.id.txtNombreTutor);
            horarios = itemView.findViewById(R.id.spinnerHorarios);
            btnSolicitar = itemView.findViewById(R.id.btnSolicitarMonitoria);
        }

        void setOnClickListener()
        {
            btnSolicitar.setOnClickListener(this);
        }

        @Override
        public void onClick(View view)
        {
            if(view.getId() == R.id.btnSolicitarMonitoria)
            {

                Tutor tutorActual = null;
                String textoHorario = "";
                String dia = "";
                boolean encontro = false;

                for(int i = 0; i < tutores.size() && !encontro; i++)
                {
                    tutorActual = tutores.get(i);
                    if(tutorActual.getNombrePersonal().equals(nombreTutor.getText()))
                    {
                        encontro = true;
                    }
                }

                for(int j = 0; j < tutorActual.getHorarios().size(); j++)
                {
                    if(tutorActual.getHorarios().get(j).toString().equals(horarios.getSelectedItem().toString()))
                    {
                        textoHorario = horarios.getSelectedItem().toString().substring(0, 13).replace("-", "a");
                        dia = horarios.getSelectedItem().toString().substring(13).toLowerCase().trim();
                    }
                }



                String mensajeMoviApp = nombreAsignaturaActual + " el día " + dia + " de " + textoHorario;
                String url = ServicioMoniApp.solicitarMonitoria(tutorActual, mensajeMoviApp);
                Uri uriUrl = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uriUrl);
                context.startActivity(intent);
            }
        }
    }
}