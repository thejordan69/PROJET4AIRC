package model;

public class CarteNoire extends AbstractCarte {
    
    public CarteNoire(Couleur couleur,Coord coord) {
        super(couleur,coord);
    }
    
    public boolean isMoveOk(int xFinal, int yFinal){
        return ((xFinal == this.getX() && yFinal != this.getY()) || (xFinal != this.getX() && yFinal == this.getY()));
    }   
    
    public boolean move(int xFinal, int yFinal){
        this.coordonnees.setX(xFinal);
        this.coordonnees.setY(yFinal);
        return true;
    }
}
