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
    private ArrayList<Tutor> tutoresDisponibles;
    private TextView totalTutores;
    private ServicioMoniApp servicioMoniApp;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);
        servicioMoniApp =(ServicioMoniApp) getIntent().getSerializableExtra("ServicioMoniApp");

        rvTutores = findViewById(R.id.rvTutores);
        spinnerAsignaturas = findViewById(R.id.spinnerAsignaturas);
        totalTutores = findViewById(R.id.txtTutoresDisponibles);

        //Configura el spinner
        ArrayList<String> asignaturas = servicioMoniApp.listaNombreAsignaturas();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_personalizado, asignaturas);
        spinnerAsignaturas.setAdapter(arrayAdapter);

        //Configura el recyclerView
        rvTutores.setLayoutManager(new LinearLayoutManager(this));
        final TutorAdapter tutorAdapter = new TutorAdapter();
        tutoresDisponibles = new ArrayList<>();

        //Evento encargado del spinner
        spinnerAsignaturas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String nombreAsignatura = spinnerAsignaturas.getSelectedItem().toString().trim();
                tutoresDisponibles.clear();

                tutoresDisponibles = servicioMoniApp.buscarTutotesDisponible(nombreAsignatura);
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
}
