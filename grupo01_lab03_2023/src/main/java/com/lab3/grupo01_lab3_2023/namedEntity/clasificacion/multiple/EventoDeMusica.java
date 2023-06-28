package com.lab3.grupo01_lab3_2023.namedEntity.clasificacion.multiple;

import com.lab3.grupo01_lab3_2023.namedEntity.clasificacion.entity.Evento;
import com.lab3.grupo01_lab3_2023.namedEntity.clasificacion.tema.cultura.Musica;

public class EventoDeMusica extends Evento implements Musica{
    public EventoDeMusica(String name, String category, int frequency, String forma_canonica, String fecha, String recurrente){
        super(name,category, frequency,forma_canonica,fecha,recurrente);
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
        return "Evento";
    }
    

}
