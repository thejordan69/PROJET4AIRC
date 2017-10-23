package model;

import java.awt.Color;
import java.util.ArrayList;

public class EquipeIHM implements InterfaceEquipeIHM {
    private Equipe equipe;
    
    public EquipeIHM(Equipe equipe){
        this.equipe = equipe;
    } 

    @Override
    public String getScore() {
        return String.valueOf(equipe.getScore());
    }

    @Override
    public ArrayList<Joueur> getJoueurs() {
        return equipe.getJoueurs();
    }

    @Override
    public Color getCouleur() {
        return equipe.getCouleur();
    }
}
