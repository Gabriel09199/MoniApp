package com.example.moniapp.activities;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moniapp.R;
import com.example.moniapp.mundo.Tutor;
import com.example.moniapp.servicios.ServicioMoniApp;

public class ActivityLogin extends AppCompatActivity
{
    public static final int REQUEST_CODE_INICIAR_SESION = 0;
    public static final String ACTUALIZAR_TUTOR = "Actualizar Tutor";

    private EditText txtUsername;
    private EditText txtContraseña;
    private Button btnIngresarSesion;
    private ServicioMoniApp servicioMoniApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        servicioMoniApp =(ServicioMoniApp) getIntent().getSerializableExtra("ServicioMoniApp");

        txtUsername = (EditText) findViewById(R.id.txtUsername);
        txtContraseña = (EditText) findViewById(R.id.txtContraseña);
        btnIngresarSesion = (Button) findViewById(R.id.btnIniciarSesion);
    }

    public void btnIniciarSesion(View view)
    {
        Intent intent= new Intent(this, ActivityTutor.class);
        String nombreUsuario = txtUsername.getText().toString().trim();
        String password = txtContraseña.getText().toString().trim();
        Tutor tutor = servicioMoniApp.buscarTutorPorUsuario(nombreUsuario);

        if(tutor != null && nombreUsuario.equals(tutor.getNombreUsuario()) &&
                password.equals(tutor.getPassword()))
        {
            Bundle bundle = new Bundle();
            bundle.putSerializable("tutor", tutor);
            intent.putExtras(bundle);
            startActivityForResult(intent, REQUEST_CODE_INICIAR_SESION);
        }
        else
        {
            Toast.makeText(this,"El nombre de usuario o contraseña inválidos.", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(requestCode == REQUEST_CODE_INICIAR_SESION)
        {
            if(resultCode == RESULT_OK)
            {
                Tutor tutor = (Tutor) data.getSerializableExtra(ActivityTutor.TUTOR_ACTUAL);
                Log.w("ActivityLogin", tutor.toString());

                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putSerializable(ACTUALIZAR_TUTOR, tutor);
                intent.putExtras(bundle);
                setResult(RESULT_OK, intent);
                finish();
            }
        }
    }
}
