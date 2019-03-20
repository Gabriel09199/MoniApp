package com.example.moniapp.activities;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.moniapp.R;
import com.example.moniapp.adapters.TimePickerFragment;
import com.example.moniapp.mundo.Horario;

import java.util.Calendar;

public class ActivityRegistroHorario extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener
{
    public final static String NUEVO_HORARIO = "Nuevo Horario";

    private Spinner spinnerWeekDays;
    private String txtWeekDay;
    private Button btnHoraInicio;
    private Button btnHoraFin;
    private String[] weekDays;
    private String txtHora;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_horario);

        txtHora = "";
        spinnerWeekDays = findViewById(R.id.spinnerWeekDays);
        txtWeekDay = new String();
        btnHoraInicio = findViewById(R.id.btnHoraInicio);
        btnHoraFin = findViewById(R.id.btnHoraFin);

        //Personalización del spinner
        weekDays();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_personalizado, weekDays);
        spinnerWeekDays.setAdapter(arrayAdapter);


        btnHoraInicio.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(ActivityRegistroHorario.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        if(selectedMinute < 10)
                        {
                            btnHoraInicio.setText( selectedHour + ":0" + selectedMinute);
                        }
                        else
                        {
                            btnHoraInicio.setText( selectedHour + ":" + selectedMinute);
                        }

                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Hora de fin");
                mTimePicker.show();
            }
        });

        btnHoraFin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(ActivityRegistroHorario.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        if(selectedMinute < 10)
                        {
                            btnHoraFin.setText( selectedHour + ":0" + selectedMinute);
                        }
                        else
                        {
                            btnHoraFin.setText( selectedHour + ":" + selectedMinute);
                        }

                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Hora de fin");
                mTimePicker.show();
            }
        });
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
            Horario horario = new Horario(txtWeekDay, btnHoraInicio.getText().toString(), btnHoraFin.getText().toString());
            Intent intent = new Intent();
            intent.putExtra(NUEVO_HORARIO, horario);
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    public boolean verificarCamposVacios()
    {
        if (TextUtils.isEmpty(btnHoraInicio.getText()) || TextUtils.isEmpty(btnHoraFin.getText()))
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

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        if(minute < 10)
        {
            txtHora= hourOfDay+ ":0" + minute;
        }
        else
        {
            txtHora= hourOfDay+ ":" + minute;
        }
    }
}
