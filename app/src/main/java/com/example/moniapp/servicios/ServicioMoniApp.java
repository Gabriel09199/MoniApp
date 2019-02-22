package com.example.moniapp.servicios;

import com.example.moniapp.mundo.Monitoria;
import com.example.moniapp.mundo.Tutor;

import java.util.ArrayList;

public class ServicioMoniApp
{
    public final static String API_WHATSAPP = "https://api.whatsapp.com/send?phone=";
    public final static String MENSAJE_TEXTO = "&text=Hola,%20¿Qué%20tal?%20estuve%20viendo%20MoviApp%20y%20me%20gustaría%20solicitar%20la%20siguiente%20monitoria:%20";


    private ArrayList<Tutor> tutores;

    public ServicioMoniApp()
    {
        tutores = new ArrayList<Tutor>();
        Tutor tutor1 = new Tutor("Sergio Celemín", "+573213735529");
        Tutor tutor2 = new Tutor("Gabriel Montalvo", "+573163247411");

        tutores.add(tutor1);
        tutores.add(tutor2);
    }

    public String solicitarMonitoria(Tutor tutor, String horario)
    {
        String mensajeHorario = horario.replace(" ", "%20");
        String mensajeWhatsapp = API_WHATSAPP + tutor.getNumeroTelefono() + MENSAJE_TEXTO + mensajeHorario + "%20";
        return mensajeWhatsapp;
    }

    public ArrayList<Tutor> getTutores()
    {
        return tutores;
    }

    public ArrayList<Monitoria> buscarMonitorias(String nombreMonitoria)
    {
        ArrayList<Monitoria> monitoriasDisponibles = new ArrayList<>();
        Monitoria actualMonitoria = null;
        for(int i = 0; i < tutores.size(); i++)
        {
            actualMonitoria = tutores.get(i).buscarMonitoria(nombreMonitoria);
            if(actualMonitoria != null)
            {
                monitoriasDisponibles.add(actualMonitoria);
            }
        }

        return monitoriasDisponibles;
    }

    public int buscarMonitoria(String nombreMonitoria)
    {
        int total = 0;
        for(int i = 0; i < tutores.size(); i++)
        {
            if(tutores.get(i).buscarMonitoria(nombreMonitoria) != null)
            {
                total++;
            }
        }

        return total;
    }
}
