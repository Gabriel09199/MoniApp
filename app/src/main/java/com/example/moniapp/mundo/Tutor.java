package com.example.moniapp.mundo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tutor implements Serializable
{
    private String nombrePersonal;
    private String nombreUsuario;
    private String password;
    private String numeroTelefono;
    private ArrayList<Asignatura> asignaturas;
    private ArrayList<Horario> horarios;

    public Tutor(String nombrePersonal, String nombreUsuario, String password, String numeroTelefono)
    {
        this.nombrePersonal = nombrePersonal;
        this.nombreUsuario = nombreUsuario;
        this.password = password;
        this.numeroTelefono = numeroTelefono;
        this.asignaturas = new ArrayList<Asignatura>();
        this.horarios = new ArrayList<Horario>();

        Asignatura asignatura1 = new Asignatura("Apo1");
        Asignatura asignatura2 = new Asignatura("Apo2");
        Asignatura asignatura3 = new Asignatura("Fundamentos de Matemáticas");
        Asignatura asignatura4 = new Asignatura("Algebra Lineal");

        Horario horario1 = new Horario("Lunes", "10:00", "12:00");
        Horario horario2 = new Horario("Jueves", "15:00", "18:00");
        Horario horario3 = new Horario("Miercoles", "14:00", "18:00");
        Horario horario4 = new Horario("Viernes", "08:00", "09:00");
        Horario horario5 = new Horario("Martes", "09:00", "11:30");

        asignaturas.add(asignatura1);
        asignaturas.add(asignatura2);
        asignaturas.add(asignatura3);
        asignaturas.add(asignatura4);

        horarios.add(horario1);
        horarios.add(horario2);
        horarios.add(horario3);
        horarios.add(horario4);
        horarios.add(horario5);

        Collections.sort(asignaturas);
        Collections.sort(horarios);
    }


    public String getNombrePersonal() {
        return nombrePersonal;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public String getNumeroTelefono()
    {
        return numeroTelefono;
    }

    public ArrayList<Asignatura> getAsignaturas()
    {
        return asignaturas;
    }

    public ArrayList<Horario> getHorarios() { return horarios; }

    public boolean agregarMonitoria(Asignatura asignatura)
    {
        if(asignaturas.contains(asignatura))
        {
            return false;
        }

        asignaturas.add(asignatura);
        Collections.sort(asignaturas);
        return true;
    }

    public boolean agregarHorario(Horario horario)
    {
        if(horarios.contains(horario) || horario.getHoraInicio().compareTo(horario.getHoraFin()) >= 0)
        {
            return false;
        }

        horarios.add(horario);
        Collections.sort(horarios);

        return true;
    }

    public void eliminarAsignatura(Asignatura asignatura)
    {
        if(asignaturas.contains(asignatura))
        {
            asignaturas.remove(asignatura);
        }
    }

    public boolean existeAsignatura(String nombreAsignatura)
    {
        for(int i = 0; i < asignaturas.size(); i++)
        {
            if(nombreAsignatura.equals(asignaturas.get(i).getNombre()))
            {
                return true;
            }
        }

        return false;
    }

}
