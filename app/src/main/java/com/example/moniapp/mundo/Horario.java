package com.example.moniapp.mundo;

import android.view.View;

import java.io.Serializable;

public class Horario implements Comparable<Horario>, Serializable
{
    public final static String LUNES = "Lunes";
    public final static String MARTES = "Martes";
    public final static String MIERCOLES = "Miercoles";
    public final static String JUEVES = "Jueves";
    public final static String VIERNES = "Viernes";
    public final static String SABADO = "Sábado";

    private String diaSemana;
    private String horaInicio;
    private String horaFin;
    private int orden;
    private boolean disponible;

    public Horario(String diaSemana, String horaInicio, String horaFin)
    {
        this.diaSemana = diaSemana;
        this.disponible = true;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;

        ordenDias();
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible)
    {
        this.disponible = disponible;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public int getOrden()
    {
        return orden;
    }

    @Override
    public int compareTo(Horario otroHorario)
    {
        int resultado = 0;
        if(orden > otroHorario.getOrden())
        {
            resultado = 1;
        }
        else if(orden < otroHorario.getOrden())
        {
            resultado = -1;
        }
        else
        {
            resultado = horaInicio.compareTo(horaFin);
        }

        return resultado;
    }

    public void ordenDias()
    {
        if(diaSemana.equalsIgnoreCase(LUNES))
        {
            orden = 1;
        }
        else if(diaSemana.equalsIgnoreCase(MARTES))
        {
            orden = 2;
        }
        else if(diaSemana.equalsIgnoreCase(MIERCOLES))
        {
            orden = 3;
        }
        else if(diaSemana.equalsIgnoreCase(JUEVES))
        {
            orden = 4;
        }
        else if(diaSemana.equalsIgnoreCase(VIERNES))
        {
            orden = 5;
        }
        else if(diaSemana.equalsIgnoreCase(SABADO))
        {
            orden = 6;
        }
    }

    @Override
    public String toString()
    {
        String letra = diaSemana.charAt(0) + "";
        return letra.toUpperCase() + diaSemana.substring(1) + " de " + horaInicio + " a " + horaFin;
    }
}
