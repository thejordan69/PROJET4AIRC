package controler;

import java.util.ArrayList;
import model.AbstractCarte;
import model.Coord;
import model.Couleur;
import model.Joueur;

public interface InterfaceControler {

	public boolean move(Coord initCoord, Coord finalCoord);
        
        public ArrayList<AbstractCarte> getListeCartes();
        
        public Couleur getCarteCouleur(Coord coords);
        
        public boolean isEnd();
        
        public ArrayList<Joueur> getListeJoueurs();
        
        public Joueur getJoueurCourant();   
}
