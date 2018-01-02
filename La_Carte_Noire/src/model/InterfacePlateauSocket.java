package model;

import model.*;
import java.util.ArrayList;

public interface InterfacePlateauSocket {	

    public abstract boolean move (int xInit, int yInit, int xFinal, int yFinal); 

    public abstract boolean isEnd();

    public abstract Couleur getCarteCouleur(int x, int y);

    public abstract boolean isMoveOK(int xInit, int yInit, int xFinal, int yFinal);

    public abstract ArrayList<AbstractCarteIHM> getListeCartesIHM();

    public abstract ArrayList<JoueurIHM> getJoueursIHM();

    public abstract JoueurIHM getJoueurCourantIHM();

    public abstract String getGagnant();
    
    public abstract void saveScores();
    
    public abstract String getMessage();
}
