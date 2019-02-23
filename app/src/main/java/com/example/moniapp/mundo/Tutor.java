package com.example.moniapp.mundo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tutor
{
    private String nombre;
    private String numeroTelefono;
    private ArrayList<Monitoria> monitorias;
    private ArrayList<Horario> horarios;

    public Tutor(String nombre, String numeroTelefono)
    {
        this.nombre = nombre;
        this.numeroTelefono = numeroTelefono;
        this.monitorias = new ArrayList<Monitoria>();
        this.horarios = new ArrayList<Horario>();

        Monitoria monitoria1 = new Monitoria("Apo1", 10000);
        Monitoria monitoria2 = new Monitoria("Apo2", 15000);
        Monitoria monitoria3 = new Monitoria("Fundamentos de Matemáticas", 8000);
        Monitoria monitoria4 = new Monitoria("Algebra Lineal", 10000);

        Horario horario1 = new Horario("Lunes", "10:00", "12:00");
        Horario horario2 = new Horario("Jueves", "15:00", "18:00");
        Horario horario3 = new Horario("Miercoles", "14:00", "18:00");
        Horario horario4 = new Horario("Viernes", "08:00", "09:00");
        Horario horario5 = new Horario("Martes", "09:00", "11:30");

        monitorias.add(monitoria1);
        monitorias.add(monitoria2);
        monitorias.add(monitoria3);
        monitorias.add(monitoria4);

        horarios.add(horario1);
        horarios.add(horario2);
        horarios.add(horario3);
        horarios.add(horario4);
        horarios.add(horario5);

        Collections.sort(monitorias);
        Collections.sort(horarios);
    }

    public String getNombre()
    {
        return nombre;
    }

    public String getNumeroTelefono()
    {
        return numeroTelefono;
    }

    public ArrayList<Monitoria> getMonitorias()
    {
        return monitorias;
    }

    public ArrayList<Horario> getHorarios() { return horarios; }

    public boolean agregarMonitoria(Monitoria monitoria)
    {
        if(monitorias.contains(monitoria))
        {
            return false;
        }

        monitorias.add(monitoria);
        Collections.sort(monitorias);
        return true;
    }

    public boolean agregarHorario(Horario horario)
    {
        if(horarios.contains(horario) || horario.getHoraInicio().compareTo(horario.getHoraFin()) >= 0 || horario.verificarCupo())
        {
            return false;
        }

        horarios.add(horario);
        horario.aumentarCupo();
        Collections.sort(horarios);

        return true;
    }

}
