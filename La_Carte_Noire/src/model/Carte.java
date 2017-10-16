package model;

public class Carte extends AbstractCarte {

    public Carte(Couleur couleur, Coord coordonnees) {
        super(couleur, coordonnees);
    }

    public void eliminer(){
        this.coordonnees.x = -1;
        this.coordonnees.y = -1;
    }
}
