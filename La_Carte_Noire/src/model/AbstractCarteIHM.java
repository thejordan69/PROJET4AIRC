package model;

public class AbstractCarteIHM implements InterfaceAbstractCarteIHM {
    private AbstractCarte carte;
    
    public AbstractCarteIHM (AbstractCarte carte){
        this.carte = carte;
    }
    
    @Override
    public Couleur getCouleur() {
        return carte.getCouleur();
    }

    @Override
    public int getX() {
        return carte.getX();
    }

    @Override
    public int getY() {
        return carte.getY();
    } 
}
