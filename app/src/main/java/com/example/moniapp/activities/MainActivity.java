package com.example.moniapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.moniapp.R;
import com.example.moniapp.servicios.ServicioMoniApp;

public class MainActivity extends AppCompatActivity
{
    private ServicioMoniApp servicio;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        servicio = new ServicioMoniApp();
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
