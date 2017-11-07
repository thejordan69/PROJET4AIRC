package model;

public class CarteNoire extends AbstractCarte {
    
    public CarteNoire(Couleur couleur,Coord coord) {
        super(couleur,coord);
    }
    
    public boolean isMoveOk(int xFinal, int yFinal){
        //on test si le déplacement est en diagonal
        int diffX = Math.abs(xFinal-this.getX());
        int diffY = Math.abs(yFinal-this.getY());	
	return (diffX != diffY);
    }    
    
    public boolean move(int xFinal, int yFinal){
        this.coordonnees.setX(xFinal);
        this.coordonnees.setY(yFinal);
        return true;
    }
}
