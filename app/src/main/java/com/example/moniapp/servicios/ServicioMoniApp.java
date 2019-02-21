package com.example.moniapp.servicios;

import com.example.moniapp.mundo.Tutor;

import java.util.ArrayList;

public class ServicioMoniApp
{
    private ArrayList<Tutor> tutores;

    public ServicioMoniApp()
    {
        tutores = new ArrayList<Tutor>();
        Tutor tutor1 = new Tutor("Sergio Celemín", "+573213735529");
        Tutor tutor2 = new Tutor("Gabriel Montalvo", "+573163247411");

        tutores.add(tutor1);
        tutores.add(tutor2);
    }

    public void solicitarMonitoria()
    {

    }

    public ArrayList<Tutor> getTutores()
    {
        return tutores;
    }

    public int totalMonitoriasPorTutores(String nombreMateria)
    {
        int total = 0;
        for(int i = 0; i < tutores.size(); i++)
        {
            if(tutores.get(i).existeMonitoria(nombreMateria))
            {
                total++;
            }
        }

        return total;
    }
}
