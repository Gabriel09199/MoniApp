package com.example.moniapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.example.moniapp.R;
import com.example.moniapp.adapters.ClaseAdapter;
import com.example.moniapp.adapters.HorarioAdapter;
import com.example.moniapp.mundo.Asignatura;
import com.example.moniapp.mundo.Horario;
import com.example.moniapp.servicios.ServicioMoniApp;

import java.util.ArrayList;
import java.util.List;

public class ActivityTutor extends AppCompatActivity {


    public static  final int REQUEST_CODE = 1;
    private RecyclerView recyclerTutor;


    private ArrayList<Asignatura> asignaturas;
    private ArrayList<Horario> horarios;

    private Spinner spinnerOpcionesTutor;
    //private FloatingActionButton floatAgregar;
    private ServicioMoniApp servicioMoniApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor);
        recyclerTutor = findViewById(R.id.reclyclerTutor);
        asignaturas = new ArrayList<>();
        horarios = new ArrayList<>();
        servicioMoniApp =(ServicioMoniApp) getIntent().getSerializableExtra("ServicioMoniApp");


        spinnerOpcionesTutor = findViewById(R.id.spinnerOpcionesTutor);
      /*
        floatAgregar =  findViewById(R.id.floatAgregar);

        floatAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (spinnerOpcionesTutor.getSelectedItem().toString().equals("Mis clases")) {
                    Intent intent = new Intent(ActivityTutor.this
                            , ActivityRegistroClase.class);
                    startActivityForResult(intent, REQUEST_CODE);
                } else {
                    Intent intent = new Intent(ActivityTutor.this
                            , ActivityRegistroHorario.class);
                    startActivityForResult(intent, REQUEST_CODE);
                }
            }
        }
        );

        */


        spinnerOpcionesTutor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerOpcionesTutor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                if(spinnerOpcionesTutor.getSelectedItem().toString().equals("Mis clases"))
                {
                    asignaturas.clear();
                    cargarRecyclerAsignaturas();
                }
                else if(spinnerOpcionesTutor.getSelectedItem().toString().equals("Mis horarios"))
                {
                    horarios.clear();
                    cargarRecyclerHorario();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });





        //





    }

    private void cargarRecyclerHorario()
    {
        horarios = servicioMoniApp.darHorariosPorTutor("Sergio Celemín");
        recyclerTutor.setLayoutManager(new LinearLayoutManager(this));
        final HorarioAdapter horarioAdapter = new HorarioAdapter();
        recyclerTutor.setAdapter(horarioAdapter);
        horarioAdapter.setHorarios(horarios);


    }

    private void cargarRecyclerAsignaturas() {

        asignaturas = servicioMoniApp.darAsignaturasPorTutor("Sergio Celemín");
        recyclerTutor.setLayoutManager(new LinearLayoutManager(this));

        final ClaseAdapter claseAdapter = new ClaseAdapter();
        recyclerTutor.setAdapter(claseAdapter);
        claseAdapter.setClases(asignaturas);

        //asignaturas = new ArrayList<>();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {







    }//onActivityResult






 }


