package com.lab3.grupo01_lab3_2023.namedEntity.clasificacion.entity;

import com.lab3.grupo01_lab3_2023.namedEntity.NamedEntity;
// precisa, forma canónica

public class Fecha extends NamedEntity{
    String precisa;
    String forma_canonica;
    public Fecha(String name,String category, int frequency,String precisa,String forma_canonica) { 
        super(name,category, frequency);
        this.precisa = precisa;
        this.forma_canonica = forma_canonica;
    }
    public String getPrecisa() {
        return precisa;
    }
    public String getForma_canonica() {
        return forma_canonica;
    }

    
}
