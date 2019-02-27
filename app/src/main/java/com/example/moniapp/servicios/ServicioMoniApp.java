package com.example.moniapp.servicios;

import com.example.moniapp.mundo.Asignatura;
import com.example.moniapp.mundo.Horario;
import com.example.moniapp.mundo.Tutor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ServicioMoniApp implements Serializable
{
    public final static String API_WHATSAPP = "https://api.whatsapp.com/send?phone=";
    public final static String MENSAJE_TEXTO = "&text=Hola,%20¿Qué%20tal?%20estuve%20viendo%20MoviApp%20y%20me%20gustaría%20solicitar%20la%20siguiente%20monitoria:%20";

    private ArrayList<Tutor> tutores;

    public ServicioMoniApp()
    {
        tutores = new ArrayList<Tutor>();
        Tutor tutor1 = new Tutor("Sergio Celemín", "sergio123", "sergio123", "+573213735529");
        Tutor tutor2 = new Tutor("Gabriel Montalvo", "gabriel123", "gabriel123", "+573163247411");

        Asignatura asignatura = new Asignatura("Métodos Numéricos");
        tutor1.agregarMonitoria(asignatura);

        tutores.add(tutor1);
        tutores.add(tutor2);
    }

    public static String solicitarMonitoria(Tutor tutor, String mensaje)
    {
        String mensajeHorario = mensaje.replace(" ", "%20");
        String url = API_WHATSAPP + tutor.getNumeroTelefono() + MENSAJE_TEXTO + mensaje + "%20";
        return url;
    }

    public ArrayList<Tutor> getTutores()
    {
        return tutores;
    }

    public ArrayList<String> listaNombreAsignaturas()
    {
        ArrayList<String> listaNombreAsignaturas = new ArrayList<>();
        String nombreActual = "";
        for(int i = 0; i < tutores.size(); i++)
        {
            for(int j = 0; j < tutores.get(i).getAsignaturas().size(); j++)
            {
                nombreActual = tutores.get(i).getAsignaturas().get(j).getNombre();
                if(!listaNombreAsignaturas.contains(nombreActual))
                {
                    listaNombreAsignaturas.add(nombreActual);
                }
            }
        }

        return listaNombreAsignaturas;
    }

    public ArrayList<Tutor> buscarTutotesDisponible(String nombreAsignatura)
    {
        ArrayList<Tutor> tutoresDisponibles = new ArrayList<>();
        Tutor tutorActual = null;
        for(int i = 0; i < tutores.size(); i++)
        {
            tutorActual = tutores.get(i);
            if(tutorActual.existeAsignatura(nombreAsignatura) && !tutoresDisponibles.contains(tutorActual))
            {
                tutoresDisponibles.add(tutorActual);
            }
        }

        return tutoresDisponibles;
    }

    public Tutor buscarTutorPorUsuario(String nombreUsuario)
    {
        Tutor tutor = null;
        for(int i = 0; i < tutores.size(); i++)
        {
            if(nombreUsuario.equalsIgnoreCase(tutores.get(i).getNombreUsuario()))
            {
                tutor = tutores.get(i);
            }
        }

        return tutor;
    }

    public void actualizarTutor(Tutor tutor)
    {
        for(int i = 0; i < tutores.size(); i++)
        {
            if(tutores.get(i).getNombreUsuario().equals(tutor.getNombreUsuario()))
            {
                tutores.set(i, tutor);
            }
        }
    }
}
