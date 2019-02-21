package com.example.moniapp.mundo;

import java.util.ArrayList;
import java.util.List;

public class Tutor
{
    private String nombre;
    private String numeroTelefono;
    private List<Monitoria> monitorias;

    public Tutor(String nombre, String numeroTelefono)
    {
        this.nombre = nombre;
        this.numeroTelefono = numeroTelefono;
        this.monitorias = new ArrayList<Monitoria>();

        Monitoria monitoria1 = new Monitoria("Apo1", "Lunes: 2:00 pm a 4:00 pm");
        Monitoria monitoria2 = new Monitoria("Apo2", "Lunes: 4:00 pm a 6:00 pm");
        Monitoria monitoria3 = new Monitoria("Fundamentos de Matemáticas", "Jueves: 9:00 am a 12:00 pm");
        Monitoria monitoria4 = new Monitoria("Algebra Lineal", "Viernes: 8:00 am a 9:00 am");

        monitorias.add(monitoria1);
        monitorias.add(monitoria2);
        monitorias.add(monitoria3);
        monitorias.add(monitoria4);
    }

    public String getNombre()
    {
        return nombre;
    }

    public String getNumeroTelefono()
    {
        return numeroTelefono;
    }

    public List<Monitoria> getMonitorias()
    {
        return monitorias;
    }

    public boolean agregarMonitoria(Monitoria monitoria)
    {
        boolean agrego = false;
        boolean existe = false;
        for(int i = 0; i < monitorias.size() && !existe; i++)
        {
            if(monitorias.get(i).getNombre().equals(monitoria.getNombre()) &&
                    !monitorias.get(i).getHorario().contains(monitoria.getHorario().get(0)))
            {
                monitorias.get(i).agregarHorario(monitoria.getHorario().get(0));
                agrego = true;
            }
            else
            {
                existe = true;
            }
        }

        if(!agrego && !existe)
        {
            monitorias.add(monitoria);
            agrego = true;
        }

        return agrego;
    }

    public boolean existeMonitoria(String nombreMonitoria)
    {
        for(int i = 0; i < monitorias.size(); i++)
        {
            if(monitorias.get(i).getNombre().equals(nombreMonitoria))
            {
                return true;
            }
        }

        return false;
    }


    public void eliminarMonitoria(Monitoria monitoria)
    {
        monitorias.remove(monitoria);
    }

    public void eliminarMonitorias()
    {
        monitorias.clear();
    }
}
