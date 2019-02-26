package com.example.moniapp.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.moniapp.R;
import com.example.moniapp.servicios.ServicioMoniApp;

public class MainActivity extends AppCompatActivity
{
    private ServicioMoniApp servicio;
    private Typeface fontBold;
    private TextView txtNameApp;
    private Button btnTutor;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        servicio = new ServicioMoniApp();

        txtNameApp = (TextView) findViewById(R.id.txtNameApp);
        btnTutor = (Button) findViewById(R.id.btnIniciar);
        fontBold = Typeface.createFromAsset(getAssets(),"Montserrat-Bold.ttf");
        txtNameApp.setTypeface(fontBold);
        btnTutor.setTypeface(fontBold);
    }

    public void btnIniciarLogin(View view)
    {
        Intent intent= new Intent(this, ActivityLogin.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("ServicioMoniApp", servicio);
        intent.putExtras(bundle);
        startActivity(intent);


    }


    public void btnIniciarVistaUsuario(View view)
    {
        Intent intent= new Intent(this, ActivityUsuario.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("ServicioMoniApp", servicio);
        intent.putExtras(bundle);
        startActivity(intent);
    }

}
