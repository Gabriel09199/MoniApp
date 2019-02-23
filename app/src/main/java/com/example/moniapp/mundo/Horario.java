package com.example.moniapp.mundo;

public class Horario implements Comparable<Horario>
{
    private String diaSemana;
    private boolean disponible;
    private String horaInicio;
    private String horaFin;
    private int totalCupos;

    public Horario(String diaSemana, String horaInicio, String horaFin)
    {
        this.diaSemana = diaSemana;
        this.disponible = true;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        totalCupos = 0;
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

    public boolean aumentarCupo()
    {
        boolean aumento = false;
        if(verificarCupo())
        {
            totalCupos++;
            aumento = true;
        }

        return aumento;
    }

    public boolean verificarCupo()
    {
        boolean cupoLLeno = false;

        if(totalCupos == 4)
        {
            cupoLLeno = true;
        }

        return cupoLLeno;
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
}
