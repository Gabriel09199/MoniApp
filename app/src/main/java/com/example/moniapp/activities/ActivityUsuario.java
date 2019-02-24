package com.example.moniapp.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.moniapp.R;
import com.example.moniapp.adapters.TutorAdapter;
import com.example.moniapp.mundo.Horario;
import com.example.moniapp.mundo.Tutor;
import com.example.moniapp.servicios.ServicioMoniApp;

import java.util.ArrayList;

public class ActivityUsuario extends AppCompatActivity
{
    private RecyclerView rvTutores;
    private Spinner spinnerAsignaturas;
    private ArrayList<Tutor> tutores;
    private ArrayList<Tutor> tutoresDisponibles;
    private TextView totalTutores;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);

        rvTutores = findViewById(R.id.rvTutores);
        spinnerAsignaturas = findViewById(R.id.spinnerAsignaturas);
        totalTutores = findViewById(R.id.txtTutoresDisponibles);

        inicializarSpinner();

        rvTutores.setLayoutManager(new LinearLayoutManager(this));
        final TutorAdapter tutorAdapter = new TutorAdapter();
        tutores = (ArrayList<Tutor>) getIntent().getSerializableExtra("tutores");
        tutoresDisponibles = new ArrayList<>();

        spinnerAsignaturas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                tutoresDisponibles.clear();
                for(int i = 0; i < tutores.size(); i++)
                {
                    for(int j = 0; j < tutores.get(i).getAsignaturas().size(); j++)
                    {
                        if(tutores.get(i).existeAsignatura(spinnerAsignaturas.getSelectedItem().toString().trim()) &&
                            !existeTutor(tutores.get(i).getNombreUsuario()))
                        {
                            tutoresDisponibles.add(tutores.get(i));
                        }
                    }
                }

                String cadena = "TUTORES DISPONIBLES: " + String.valueOf(tutoresDisponibles.size());
                totalTutores.setText(cadena);

                tutorAdapter.setTutores(tutoresDisponibles);
                tutorAdapter.setAsignaturaActual(spinnerAsignaturas.getSelectedItem().toString());
                rvTutores.setAdapter(tutorAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public boolean existeTutor(String nombre)
    {
        for(int i = 0; i < tutoresDisponibles.size(); i++)
        {
            if(nombre.equals(tutoresDisponibles.get(i).getNombreUsuario()))
            {
                return true;
            }
        }

        return false;
    }

    private void inicializarSpinner()
    {
        ArrayList<String> asignaturas = (ArrayList<String>) getIntent().getSerializableExtra("listaMaterias");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, asignaturas);
        spinnerAsignaturas.setAdapter(arrayAdapter);
    }
}
