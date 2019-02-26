package com.example.moniapp.activities;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;


import com.example.moniapp.R;
import com.example.moniapp.servicios.ServicioMoniApp;


public class ActivityRegistroClase extends AppCompatActivity {

    private ServicioMoniApp servicio;

    private Typeface fontBold;


    private TextView txtAgregarClase;

    private Button btnAgregarClase;

    private  TextView txtNombreClase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_clase);
        servicio = new ServicioMoniApp();
        fontBold = Typeface.createFromAsset(getAssets(),"Montserrat-Bold.ttf");

        txtAgregarClase = (TextView)findViewById(R.id.txtAgregarClase);
        txtAgregarClase.setTypeface(fontBold);

        btnAgregarClase = (Button)findViewById(R.id.btnAgregarClase);
        btnAgregarClase.setTypeface(fontBold);

        txtNombreClase = (TextView)findViewById(R.id.txtNombreClase);
        txtNombreClase.setTypeface(fontBold);

    }



}
