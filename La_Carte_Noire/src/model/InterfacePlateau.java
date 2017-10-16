package model;

import java.util.ArrayList;

public interface InterfacePlateau {	

	public boolean move (int xInit, int yInit, int xFinal, int yFinal); 

	public boolean isEnd();

	public Couleur getCarteCouleur(int x, int y);
        
        public boolean isMoveOK(int xInit, int yInit, int xFinal, int yFinal);
        
        public Joueur getJoueurCourant();
        
        public ArrayList<AbstractCarte> getListeCartes();
}
