package com.example.moniapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.moniapp.R;
import com.example.moniapp.mundo.Horario;

public class ActivityRegistroHorario extends AppCompatActivity
{
    public final static String NUEVO_HORARIO = "Nuevo Horario";

    private Spinner spinnerWeekDays;
    private String txtWeekDay;
    private EditText txtHoraInicio;
    private EditText txtHoraFin;
    private String[] weekDays;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_horario);

        spinnerWeekDays = findViewById(R.id.spinnerWeekDays);
        txtWeekDay = new String();
        txtHoraInicio = findViewById(R.id.txtHoraInicio);
        txtHoraFin = findViewById(R.id.txtHoraFin);

        //Personalización del spinner
        weekDays();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_personalizado, weekDays);
        spinnerWeekDays.setAdapter(arrayAdapter);
    }

    public void btnRegistrarHorario(View view)
    {
        if(verificarCamposVacios())
        {
            Toast.makeText(ActivityRegistroHorario.this, "Faltan campos por completar.", Toast.LENGTH_SHORT).show();
        }
        else
        {
            txtWeekDay = spinnerWeekDays.getSelectedItem().toString();
            Horario horario = new Horario(txtWeekDay, txtHoraInicio.getText().toString(), txtHoraFin.getText().toString());
            Intent intent = new Intent();
            intent.putExtra(NUEVO_HORARIO, horario);
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    public boolean verificarCamposVacios()
    {
        if (TextUtils.isEmpty(txtHoraInicio.getText()) || TextUtils.isEmpty(txtHoraFin.getText()))
        {
            return true;
        }

        return false;
    }

    public void weekDays()
    {
        weekDays = new String[7];

        weekDays[0] = Horario.LUNES;
        weekDays[1] = Horario.MARTES;
        weekDays[2] = Horario.MARTES;
        weekDays[3] = Horario.MIERCOLES;
        weekDays[4] = Horario.JUEVES;
        weekDays[5] = Horario.VIERNES;
        weekDays[6] = Horario.SABADO;
    }
}
