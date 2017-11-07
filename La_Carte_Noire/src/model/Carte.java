package model;

public class Carte extends AbstractCarte {

    public Carte(Couleur couleur, Coord coordonnees) {
        super(couleur, coordonnees);
    }

    public void eliminer(){
        this.coordonnees.setX(-1);
        this.coordonnees.setY(-1);
    }
}
