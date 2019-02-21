package com.example.moniapp.mundo;

import java.util.ArrayList;
import java.util.Collections;

public class Monitoria
{
    private String nombre;
    private ArrayList<String> horarios;
    private boolean disponible;

    public Monitoria(String nombre, String horario)
    {
        this.nombre = nombre;
        this.horarios = new ArrayList<String>();
        agregarHorario(horario);
        disponible = true;
    }

    public String getNombre()
    {
        return nombre;
    }

    public ArrayList<String> getHorario()
    {
        return horarios;
    }

    public boolean getDisponible()
    {
        return disponible;
    }

    public void setDisponible()
    {
        if(disponible)
        {
            disponible = false;
        }
        else
        {
            disponible = true;
        }
    }

    public void agregarHorario(String horario)
    {
        agregarHorario(horario);
    }
}
