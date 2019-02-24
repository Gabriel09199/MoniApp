package com.example.moniapp.mundo;

import java.io.Serializable;

public class Asignatura implements Comparable<Asignatura>, Serializable
{
    private String nombre;

    public Asignatura(String nombre)
    {
        this.nombre = nombre;
    }

    public String getNombre()
    {
        return nombre;
    }

    @Override
    public int compareTo(Asignatura otraAsignatura)
    {
        return nombre.compareTo(otraAsignatura.getNombre());
    }
}
