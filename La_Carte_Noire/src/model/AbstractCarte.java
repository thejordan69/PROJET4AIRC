package model;

import java.io.Serializable;

public abstract class AbstractCarte implements Serializable {
    protected Couleur couleur;
    protected Coord coordonnees;
    
    public AbstractCarte(Couleur couleur, Coord coordonnees){
        this.couleur = couleur;
        this.coordonnees = coordonnees;
    }
    
    public Couleur getCouleur(){
        return this.couleur;
    }
    
    public int getX(){
        return this.coordonnees.getX();
    }   
    
    public int getY(){
        return this.coordonnees.getY();
    }   
}
