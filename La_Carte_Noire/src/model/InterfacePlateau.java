package model;

import java.util.ArrayList;

public interface InterfacePlateau {	

    public boolean move (int xInit, int yInit, int xFinal, int yFinal); 

    public boolean isEnd();

    public Couleur getCarteCouleur(int x, int y);

    public boolean isMoveOK(int xInit, int yInit, int xFinal, int yFinal);

    public ArrayList<AbstractCarteIHM> getListeCartesIHM();

    public ArrayList<EquipeIHM> getEquipesIHM();

    public ArrayList<JoueurIHM> getJoueursIHM();

    public JoueurIHM getJoueurCourantIHM();

    public String getGameMode();

    public String getGagnant();
    
    public void saveScores();
    
    public void moveIA();
    
    public String getMessage();
}
