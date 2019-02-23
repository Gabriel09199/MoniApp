package com.example.moniapp.mundo;

import java.util.ArrayList;
import java.util.Collections;

public class Monitoria implements Comparable<Monitoria>
{
    private String nombre;
    private int precioHora;

    public Monitoria(String nombre, int precioHora)
    {
        this.nombre = nombre;
        this.precioHora = precioHora;
    }

    public String getNombre()
    {
        return nombre;
    }

    public int getPrecioHora()
    {
        return precioHora;
    }

    @Override
    public int compareTo(Monitoria otraMonitoria)
    {
        if(nombre.equals(otraMonitoria.getNombre()))
        {
            return String.valueOf(precioHora).compareTo(String.valueOf(otraMonitoria.precioHora));
        }
        else
        {
            return nombre.compareTo(otraMonitoria.getNombre());
        }

    }
}
