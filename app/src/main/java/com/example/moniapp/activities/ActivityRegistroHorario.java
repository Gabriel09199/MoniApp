package com.example.moniapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
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

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_horario);

        spinnerWeekDays = findViewById(R.id.spinnerWeekDays);
        txtWeekDay = new String();
        txtHoraInicio = findViewById(R.id.txtHoraInicio);
        txtHoraFin = findViewById(R.id.txtHoraFin);
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
}
