package com.lab3.grupo01_lab3_2023.namedEntity.clasificacion.multiple;

import com.lab3.grupo01_lab3_2023.namedEntity.clasificacion.entity.Evento;
import com.lab3.grupo01_lab3_2023.namedEntity.clasificacion.tema.cultura.Cine;

public class EventoDeCine extends Evento implements Cine{
    public EventoDeCine(String name, String category, int frequency, String forma_canonica, String fecha, String recurrente){
        super(name,category, frequency,forma_canonica,fecha,recurrente);
    }
        
    @Override
    public String getCultura() { 
        return "Cine";
    }
    @Override
    public String getTema() {
        return "Cine";
    }
    @Override
    public String getCine() {
        return "Evento";
    }
    

}
    


