package com.example.moniapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moniapp.R;
import com.example.moniapp.servicios.ServicioMoniApp;

public class ActivityLogin extends AppCompatActivity {

    public static final String USER="usuario";

    public static final String PASSWORD="usuario";


    private TextView txtUsername;

    private TextView txtContraseña;

    private Button btnIngresar;

    private Button btnIngresarInvitado;

    private ServicioMoniApp servicioMoniApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtUsername = (TextView)findViewById(R.id.txtUsername);
        txtContraseña = (TextView)findViewById(R.id.txtContraseña);

        btnIngresar = (Button) findViewById(R.id.btnIngresar);
        btnIngresarInvitado= (Button)findViewById(R.id.btnIngresarInvitado);
        servicioMoniApp =(ServicioMoniApp) getIntent().getSerializableExtra("ServicioMoniApp");


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
        Bundle bundle = new Bundle();
        bundle.putSerializable("ServicioMoniApp", servicioMoniApp);
        intent.putExtras(bundle);
        startActivity(intent);
        /*
        if(txtUsername.toString().equals(USER) && txtContraseña.toString().equals(PASSWORD))
        {

        }
        else
            {
                Toast.makeText(this,"Usuario o contraseña Inválidos", Toast.LENGTH_LONG);

            }

            */

    }


}
