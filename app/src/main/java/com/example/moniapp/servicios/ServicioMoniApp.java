package com.example.moniapp.servicios;

import com.example.moniapp.mundo.Asignatura;
import com.example.moniapp.mundo.Tutor;

import java.util.ArrayList;
import java.util.Collections;

public class ServicioMoniApp
{
    public final static String API_WHATSAPP = "https://api.whatsapp.com/send?phone=";
    public final static String MENSAJE_TEXTO = "&text=Hola,%20¿Qué%20tal?%20estuve%20viendo%20MoviApp%20y%20me%20gustaría%20solicitar%20la%20siguiente%20monitoria:%20";

    private ArrayList<Tutor> tutores;

    public ServicioMoniApp()
    {
        tutores = new ArrayList<Tutor>();
        Tutor tutor1 = new Tutor("Sergio Celemín", "sergio123", "sergio123", "+573213735529");
        Tutor tutor2 = new Tutor("Gabriel Montalvo", "gabriel123", "gabriel123", "+573163247411");

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

    public ArrayList<Asignatura> buscarMonitorias(String nombreMonitoria)
    {
        ArrayList<Asignatura> monitoriasDisponibles = new ArrayList<>();
        Asignatura actualAsignatura = null;
        for(int i = 0; i < tutores.size(); i++)
        {
            for(int j = 0; j < tutores.get(i).getAsignaturas().size(); j++)
            {
                actualAsignatura = tutores.get(i).getAsignaturas().get(j);
                if(actualAsignatura.equals(nombreMonitoria))
                {
                    monitoriasDisponibles.add(actualAsignatura);
                }
            }
        }

        Collections.sort(monitoriasDisponibles);

        return monitoriasDisponibles;
    }

    public int totalMonitoriasDisponibles(String nombreMonitoria)
    {
        int totalMonitorias = 0;
        Asignatura actualAsignatura = null;
        for(int i = 0; i < tutores.size(); i++)
        {
            for(int j = 0; j < tutores.get(i).getAsignaturas().size(); j++)
            {
                actualAsignatura = tutores.get(i).getAsignaturas().get(j);
                if(actualAsignatura.equals(nombreMonitoria))
                {
                    totalMonitorias++;
                }
            }
        }

        return totalMonitorias;
    }

    public int totalHorasDisponibles(Tutor tutor)
    {
        int totalHoras = 0;
        String horaInicio = "";
        String horaFin = "";
        String minutosInicio = "";
        String minutosFin = "";

        for(int i = 0; i < tutor.getHorarios().size(); i++)
        {
            if(tutor.getHorarios().get(i).isDisponible())
            {
                horaInicio = tutor.getHorarios().get(i).getHoraInicio().substring(0, 1);
                minutosInicio = tutor.getHorarios().get(i).getHoraInicio().substring(1);
                horaFin = tutor.getHorarios().get(i).getHoraFin().substring(0, 1);
                minutosFin = tutor.getHorarios().get(i).getHoraFin().substring(1);

                totalHoras += (Integer.parseInt(horaFin) - Integer.getInteger(horaInicio)) +
                                (Integer.parseInt(minutosFin) - Integer.getInteger(minutosInicio));
            }
        }

        return totalHoras;
    }
}
