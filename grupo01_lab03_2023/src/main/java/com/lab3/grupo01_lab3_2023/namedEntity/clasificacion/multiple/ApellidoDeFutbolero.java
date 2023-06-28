package com.lab3.grupo01_lab3_2023.namedEntity.clasificacion.multiple;

import com.lab3.grupo01_lab3_2023.namedEntity.clasificacion.entity.persona.Apellido;
import com.lab3.grupo01_lab3_2023.namedEntity.clasificacion.tema.deportes.Futbol;

public class ApellidoDeFutbolero extends Apellido implements Futbol{
    public ApellidoDeFutbolero(String name , String category, int frequency, String ID, String forma_canonica, String origen)
    {
        super(name,category, frequency,ID,forma_canonica,origen);
    }

    @Override
    public String getTema() {
        return "Futbol";
    }

    @Override
    public String getDeportes() {
        return "Futbol";
    }

    @Override
    public String getfutbol() {
        return "Apellido";
    }


    
}
