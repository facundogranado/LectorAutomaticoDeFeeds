
package com.lab3.grupo01_lab3_2023.namedEntity.heuristic;

public class DigimonHeuristic extends Heuristic {

    @Override
    public String getHeuristicName() {
        return "DigimonHeuristic";
    }

    // comprobara si la entidad es un digimon o no
    @Override
    public boolean isEntity(String word) {
        return word.endsWith("mon") && word.substring(0, 1).compareTo(word.substring(0, 1).toUpperCase()) == 0;
    }
}