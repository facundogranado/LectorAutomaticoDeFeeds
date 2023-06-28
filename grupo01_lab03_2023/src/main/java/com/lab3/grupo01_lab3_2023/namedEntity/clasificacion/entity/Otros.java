package com.lab3.grupo01_lab3_2023.namedEntity.clasificacion.entity;

import com.lab3.grupo01_lab3_2023.namedEntity.NamedEntity;

public class Otros extends NamedEntity{
    private String comentarios;
    public Otros(String name,String category, int frequency,String comentarios)  { 
        super(name,category, frequency);
        this.comentarios = comentarios;

    }
    public String getComentarios() {
        return comentarios;
    }

    
}
