package controler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import model.AbstractCarte;
import model.Coord;
import model.Couleur;
import model.Plateau;

public class GameControler extends Observable implements InterfaceControler {
	private Plateau plateau;
	
	public GameControler(HashMap<String,Integer> mapJoueurs) {
            this.plateau = new Plateau(mapJoueurs);
            this.notifyObservers(plateau.getListeCartes()); 
	}

	@Override
	public boolean move(Coord initCoord, Coord finalCoord) {
            boolean ret = false;
            
            ret = plateau.isMoveOK(initCoord.x, initCoord.y, finalCoord.x, finalCoord.y);
            if(ret){
                ret = plateau.move(initCoord.x, initCoord.y, finalCoord.x, finalCoord.y);
            }
            
            this.notifyObservers(plateau.getListeCartes()); 
            return ret;
	}
        
        @Override
        public Couleur getCarteCouleur(Coord coords){
            return plateau.getCarteCouleur(coords.x,coords.y);
        }

       /* @Override
        public Joueur getJoueurCourant(){		
                    return plateau.getJoueurCourant();
        }*/	
        
        @Override
        public ArrayList<AbstractCarte> getListeCartes(){
            return plateau.getListeCartes();
        }
        
        @Override
	public void notifyObservers(Object arg) {
		super.setChanged();
		super.notifyObservers(arg); 
	}

	public void addObserver(Observer o){
		super.addObserver(o);
		this.notifyObservers(plateau.getListeCartes()); 
	}

    @Override
    public boolean isEnd() {
       return plateau.isEnd();
    }
    
    /*public ArrayList<Joueur> getListeJoueurs(){
        return plateau.getListeJoueurs();
    }*/
}
