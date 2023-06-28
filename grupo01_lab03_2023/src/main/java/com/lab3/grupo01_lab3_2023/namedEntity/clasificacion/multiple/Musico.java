package com.lab3.grupo01_lab3_2023.namedEntity.clasificacion.multiple;

import com.lab3.grupo01_lab3_2023.namedEntity.clasificacion.entity.persona.Persona;
import com.lab3.grupo01_lab3_2023.namedEntity.clasificacion.tema.cultura.Musica;

public class Musico extends Persona implements Musica {
    public Musico(String name , String category, int frequency, String ID)
    {
        super(name,category, frequency,ID);
    }

    @Override
    public String getCultura() {
        return "Musica";
    }

    @Override
    public String getTema() {
        return "Musica";
    }

    @Override
    public String getMusica() {
        return "Persona";
    }
    
    
}
