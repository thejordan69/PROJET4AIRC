package model;

import java.io.Serializable;

public class Jeton implements Serializable {
    private Couleur couleur;
    private int importance;
    
    public Jeton(Couleur couleur, int importance){
        this.couleur = couleur;
        this.importance = importance;
    }

    public Couleur getCouleur() {
        return couleur;
    }

    public int getImportance() {
        return importance;
    }
}
