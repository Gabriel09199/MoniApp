package com.example.moniapp.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moniapp.R;
import com.example.moniapp.mundo.Tutor;
import com.example.moniapp.servicios.ServicioMoniApp;

public class MainActivity extends AppCompatActivity
{
    public static final int REQUEST_CODE_INICIAR_TUTOR = 0;

    private ServicioMoniApp servicio;
    private Typeface fontBold;
    private TextView txtNameApp;
    private RadioButton rbTutor;
    private RadioButton rbInvitado;
    private Button btnTutor;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        servicio = new ServicioMoniApp();

        txtNameApp = findViewById(R.id.txtNameApp);
        btnTutor =  findViewById(R.id.btnIniciar);
        rbTutor = findViewById(R.id.rbTutor);
        rbInvitado = findViewById(R.id.rbInvitado);

        fontBold = Typeface.createFromAsset(getAssets(),"Montserrat-Bold.ttf");
        txtNameApp.setTypeface(fontBold);
        btnTutor.setTypeface(fontBold);
    }

    public void btnIniciar(View view)
    {
        if(rbTutor.isChecked())
        {
            Intent intent = new Intent(this, ActivityLogin.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("ServicioMoniApp", servicio);
            intent.putExtras(bundle);
            startActivityForResult(intent, REQUEST_CODE_INICIAR_TUTOR);
        }
        else
        {
            Intent intent = new Intent(this, ActivityUsuario.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("ServicioMoniApp", servicio);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(requestCode == REQUEST_CODE_INICIAR_TUTOR)
        {
            if(resultCode == RESULT_OK)
            {
                Tutor tutorActual = (Tutor) getIntent().getSerializableExtra(ActivityLogin.ACTUALIZAR_TUTOR);
                Log.w("MainActivity", tutorActual.toString());
                servicio.actualizarTutor(tutorActual);
            }
        }
    }
}
