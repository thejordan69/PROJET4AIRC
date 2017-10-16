package model;

public abstract class AbstractCarte {
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
        return this.coordonnees.x;
    }   
    
    public int getY(){
        return this.coordonnees.y;
    }   
}
