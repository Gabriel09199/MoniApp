package com.example.moniapp.activities;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.moniapp.R;
import com.example.moniapp.adapters.ClaseAdapter;
import com.example.moniapp.adapters.HorarioAdapter;
import com.example.moniapp.mundo.Asignatura;
import com.example.moniapp.mundo.Horario;
import com.example.moniapp.mundo.Tutor;

import java.util.ArrayList;
import java.util.List;

public class ActivityTutor extends AppCompatActivity
{
    public final static String MIS_CLASES = "Mis clases";
    public final static String MIS_HORARIOS = "Mis horarios";
    public final static String TUTOR_ACTUAL = "Tutor";

    public static final int REQUEST_CODE_AGREGAR_ASIGNATURA = 0;
    public static final int REQUEST_CODE_AGREGAR_HORARIO = 1;

    private RecyclerView recyclerTutor;
    private ArrayList<Asignatura> asignaturas;
    private ArrayList<Horario> horarios;
    private Spinner spinnerOpcionesTutor;
    private FloatingActionButton fabAgregar;
    private ClaseAdapter claseAdapter;
    private HorarioAdapter horarioAdapter;
    private Tutor tutor;
    private int contador;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor);
        tutor =(Tutor) getIntent().getSerializableExtra("tutor");
        contador = 0;

        fabAgregar = findViewById(R.id.fabAgregar);
        recyclerTutor = findViewById(R.id.rvTutor);
        spinnerOpcionesTutor = findViewById(R.id.spinnerOpciones);
        asignaturas = tutor.getAsignaturas();
        horarios = tutor.getHorarios();

        //Configura el recyclerTutor y los adapter y el spinner
        String[] opciones = new String[2];
        opciones[0] = MIS_CLASES;
        opciones[1] = MIS_HORARIOS;

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_tutor, opciones);
        spinnerOpcionesTutor.setAdapter(arrayAdapter);
        recyclerTutor.setLayoutManager(new LinearLayoutManager(this));
        claseAdapter = new ClaseAdapter();
        horarioAdapter = new HorarioAdapter();

        spinnerOpcionesTutor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                if(spinnerOpcionesTutor.getSelectedItem().toString().equals(MIS_CLASES))
                {
                    asignaturas = tutor.getAsignaturas();
                    claseAdapter.setClases(asignaturas);
                    recyclerTutor.setAdapter(claseAdapter);
                }
                else if(spinnerOpcionesTutor.getSelectedItem().toString().equals(MIS_HORARIOS))
                {
                    horarios = tutor.getHorarios();
                    horarioAdapter.setHorarios(horarios);
                    recyclerTutor.setAdapter(horarioAdapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT)
        {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target)
            {
                Toast.makeText(ActivityTutor.this, "onMove()", Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction)
            {
                if(spinnerOpcionesTutor.getSelectedItem().toString().equals(MIS_CLASES)) {
                    tutor.getAsignaturas().remove(viewHolder.getAdapterPosition());
                    recyclerTutor.removeViewAt(viewHolder.getAdapterPosition());
                    claseAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
                    claseAdapter.notifyDataSetChanged();

                    Toast.makeText(ActivityTutor.this, "La asignatura se ha eliminado satisfactoriamente", Toast.LENGTH_SHORT).show();
                }
                else if(spinnerOpcionesTutor.getSelectedItem().toString().equals(MIS_HORARIOS))
                {
                    tutor.getHorarios().remove(viewHolder.getAdapterPosition());
                    recyclerTutor.removeViewAt(viewHolder.getAdapterPosition());
                    horarioAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
                    horarioAdapter.notifyDataSetChanged();

                    Toast.makeText(ActivityTutor.this, "El horario se ha eliminado satisfactoriamente", Toast.LENGTH_SHORT).show();
                }
            }
        }).attachToRecyclerView(recyclerTutor);

        fabAgregar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                if (spinnerOpcionesTutor.getSelectedItem().toString().equals(MIS_CLASES))
                {
                    Intent intent = new Intent(ActivityTutor.this, ActivityRegistroClase.class);
                    startActivityForResult(intent, REQUEST_CODE_AGREGAR_ASIGNATURA);
                }
                else if(spinnerOpcionesTutor.getSelectedItem().toString().equals(MIS_HORARIOS))
                {
                    Intent intent = new Intent(ActivityTutor.this, ActivityRegistroHorario.class);
                    startActivityForResult(intent, REQUEST_CODE_AGREGAR_HORARIO);
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(requestCode == REQUEST_CODE_AGREGAR_ASIGNATURA)
        {
            if(resultCode == RESULT_OK)
            {
                Asignatura asignatura = (Asignatura) data.getSerializableExtra(ActivityRegistroClase.NUEVA_ASIGNATURA);
                boolean agregar = tutor.agregarMonitoria(asignatura);

                if(agregar)
                {
                    claseAdapter.setClases(asignaturas);
                    recyclerTutor.setAdapter(claseAdapter);
                    Toast.makeText(ActivityTutor.this, "La asignatura ha sido agregada.", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(ActivityTutor.this, "La asignatura no se ha agregado porque ya existe.", Toast.LENGTH_SHORT).show();
                }

            }
        }
        else if(requestCode == REQUEST_CODE_AGREGAR_HORARIO)
        {
            if(resultCode == RESULT_OK)
            {
                Horario horario = (Horario) data.getSerializableExtra(ActivityRegistroHorario.NUEVO_HORARIO);
                boolean agregar = tutor.agregarHorario(horario);

                if(agregar)
                {
                    horarioAdapter.setHorarios(horarios);
                    recyclerTutor.setAdapter(horarioAdapter);
                    Toast.makeText(ActivityTutor.this, "El horario ha sido agregado.", Toast.LENGTH_SHORT).show();                }
                else
                {
                    Toast.makeText(ActivityTutor.this, "El horario no se ha agregado porque ya existe.", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public void onBackPressed()
    {
        if(contador == 0)
        {
            Toast.makeText(getApplicationContext(), "Presione de nuevo para cerrar sesión", Toast.LENGTH_SHORT).show();
            contador++;
        }
        else
        {
            super.onBackPressed();
        }

        CountDownTimer countDownTimer = new CountDownTimer(3000, 2000)
        {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {

                Intent intent = new Intent();
                intent.putExtra(TUTOR_ACTUAL, tutor);
                setResult(RESULT_OK, intent);
                finish();
            }
        }.start();
    }



}


