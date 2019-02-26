package com.example.moniapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    private EditText txtUsername;
    private EditText txtContraseña;
    private Button btnIngresar;
    private Button btnIngresarInvitado;
    private ServicioMoniApp servicioMoniApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        servicioMoniApp =(ServicioMoniApp) getIntent().getSerializableExtra("ServicioMoniApp");

        txtUsername = (EditText) findViewById(R.id.txtUsername);
        txtContraseña = (EditText) findViewById(R.id.txtContraseña);
        btnIngresar = (Button) findViewById(R.id.btnIngresar);
        btnIngresarInvitado= (Button)findViewById(R.id.btnIngresarInvitado);
    }


    public void btnIniciarVistaInvitado(View view)
    {
        Intent intent= new Intent(this, ActivityUsuario.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("ServicioMoniApp", servicioMoniApp);
        intent.putExtras(bundle);
        startActivity(intent);
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
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this,"El nombre de usuario o contraseña inválidos.", Toast.LENGTH_LONG).show();
        }
    }
}
