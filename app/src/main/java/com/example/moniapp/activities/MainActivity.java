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

    private Button btnUsuario;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        servicio = new ServicioMoniApp();
        fontBold = Typeface.createFromAsset(getAssets(),"Montserrat-Bold.ttf");



        txtNameApp = (TextView) findViewById(R.id.txtNameApp);
        txtNameApp.setTypeface(fontBold);

        btnTutor = (Button) findViewById(R.id.btnTutor);
        btnTutor.setTypeface(fontBold);

        btnUsuario = (Button) findViewById(R.id.btnUsuario);
        btnUsuario.setTypeface(fontBold);




    }

    public void btnIniciarVistaUsuario(View view)
    {
        Intent intent= new Intent(this, ActivityUsuario.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("tutores", servicio.getTutores());
        bundle.putSerializable("listaMaterias", servicio.listaNombreAsignaturas());
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
