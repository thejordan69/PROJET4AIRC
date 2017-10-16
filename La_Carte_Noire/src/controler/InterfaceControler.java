package controler;

import java.util.ArrayList;
import model.AbstractCarte;
import model.Coord;
import model.Couleur;
import model.Joueur;

public interface InterfaceControler {

	public boolean move(Coord initCoord, Coord finalCoord);
        
        public Couleur getCarteCouleur(Coord coords);
        
        //public Joueur getJoueurCourant();
        
        //public Joueur getListeJoueurs();
        
        public ArrayList<AbstractCarte> getListeCartes();
        
        public boolean isEnd();
}
