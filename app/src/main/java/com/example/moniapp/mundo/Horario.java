package com.example.moniapp.mundo;

import java.io.Serializable;

public class Horario implements Comparable<Horario>, Serializable
{
    private String diaSemana;
    private String horaInicio;
    private String horaFin;
    private boolean disponible;

    public Horario(String diaSemana, String horaInicio, String horaFin)
    {
        this.diaSemana = diaSemana;
        this.disponible = true;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    @Override
    public int compareTo(Horario otroHorario)
    {
        int resultado = 0;
        if(diaSemana.equals(otroHorario.getDiaSemana()))
        {
            resultado = horaInicio.compareTo(horaFin);
        }
        else
        {
            resultado = diaSemana.compareTo(otroHorario.getDiaSemana());
        }

        return resultado;
    }

    @Override
    public String toString()
    {
        return horaInicio + " - " + horaFin + "\t\t" + diaSemana.toUpperCase();
    }
}
