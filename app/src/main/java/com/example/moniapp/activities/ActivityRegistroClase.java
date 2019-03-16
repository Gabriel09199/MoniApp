package com.example.moniapp.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.moniapp.R;
import com.example.moniapp.mundo.Asignatura;
import com.example.moniapp.servicios.ServicioMoniApp;


public class ActivityRegistroClase extends AppCompatActivity
{
    public final static String NUEVA_ASIGNATURA = "Nueva Asignatura";

    private Typeface fontBold;
    private TextView txtAgregarClase;
    private EditText txtNombreClase;
    private Button btnAgregarClase;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_clase);
        fontBold = Typeface.createFromAsset(getAssets(),"Montserrat-Bold.ttf");

        txtAgregarClase = findViewById(R.id.txtAgregarClase);
        txtNombreClase = findViewById(R.id.txtNombreClase);
        btnAgregarClase = findViewById(R.id.btnAgregarClase);

        txtAgregarClase.setTypeface(fontBold);
        txtNombreClase.setTypeface(fontBold);
        btnAgregarClase.setTypeface(fontBold);
    }

    public void btnRegistrarClase(View view)
    {
        if(verificarCamposVacios())
        {
            Toast.makeText(ActivityRegistroClase.this, "Faltan campos por completar.", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Asignatura asignatura = new Asignatura(txtNombreClase.getText().toString());
            Intent intent = new Intent();
            intent.putExtra(NUEVA_ASIGNATURA, asignatura);
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    public boolean verificarCamposVacios()
    {
        if (TextUtils.isEmpty(txtNombreClase.getText()))
        {
            return true;
        }

        return false;
    }


}
