package com.lab3.grupo01_lab3_2023.namedEntity.clasificacion.entity.persona;

import com.lab3.grupo01_lab3_2023.namedEntity.NamedEntity;

public class Persona extends NamedEntity {
    private String  ID;
    
    public Persona(String name,String category, int frequency,String ID) { 

        super(name,category, frequency);
        this.ID = ID;
    }
    public String getID() {
        return ID;
    }
    
}
