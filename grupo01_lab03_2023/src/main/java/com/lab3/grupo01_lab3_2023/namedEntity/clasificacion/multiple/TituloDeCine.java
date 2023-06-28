package com.lab3.grupo01_lab3_2023.namedEntity.clasificacion.multiple;

import com.lab3.grupo01_lab3_2023.namedEntity.clasificacion.entity.persona.Titulo;
import com.lab3.grupo01_lab3_2023.namedEntity.clasificacion.tema.cultura.Cine;

public class TituloDeCine extends Titulo implements Cine {
    public TituloDeCine(String name, String category, int frequency, String ID, String forma_canonica, String profesional)
    {
        super(name,category, frequency,ID,forma_canonica,profesional);
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
        return "Titulo";
    }
    
    
}
