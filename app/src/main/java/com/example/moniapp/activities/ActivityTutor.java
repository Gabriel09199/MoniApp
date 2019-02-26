package com.example.moniapp.activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Spinner;
import com.example.moniapp.R;
import com.example.moniapp.mundo.Asignatura;
import com.example.moniapp.mundo.Horario;

import java.util.ArrayList;

public class ActivityTutor extends AppCompatActivity {


    public static  final int REQUEST_CODE = 1;
    private RecyclerView recyclerTutor;
    private ArrayList<Asignatura> asignaturas;
    private ArrayList<Horario> horarios;

    private Spinner spinnerOpcionesTutor;
    private FloatingActionButton floatAgregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor);
        recyclerTutor = findViewById(R.id.reclyclerTutor);
        asignaturas = new ArrayList<>();
        horarios = new ArrayList<>();
        spinnerOpcionesTutor = findViewById(R.id.spinnerOpcionesTutor);
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



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {


    }//onActivityResult






 }


