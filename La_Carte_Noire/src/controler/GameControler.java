package controler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import model.AbstractCarteIHM;
import model.Coord;
import model.Couleur;
import model.EquipeIHM;
import model.JoueurIHM;
import model.Plateau;

public class GameControler extends Observable implements InterfaceControler {
	private Plateau plateau;
	
	public GameControler(HashMap<String,Integer> mapJoueurs) {
            this.plateau = new Plateau(mapJoueurs);
            this.notifyObservers(); 
	}

	@Override
	public boolean move(Coord initCoord, Coord finalCoord) {
            boolean ret = false;
            
            ret = plateau.isMoveOK(initCoord.x, initCoord.y, finalCoord.x, finalCoord.y);
            if(ret){
                ret = plateau.move(initCoord.x, initCoord.y, finalCoord.x, finalCoord.y);
            }
            
            this.notifyObservers(); 
            return ret;
	}
        
        @Override
        public Couleur getCarteCouleur(Coord coords){
            return plateau.getCarteCouleur(coords.x,coords.y);
        }
        
        @Override
        public ArrayList<AbstractCarteIHM> getListeCartesIHM(){
            return plateau.getListeCartesIHM();
        }
        
        @Override
	public void notifyObservers(Object arg) {
		super.setChanged();
		super.notifyObservers(arg); 
	}

        @Override
	public void addObserver(Observer o){
		super.addObserver(o);
		this.notifyObservers(plateau.getListeCartesIHM()); 
	}

        @Override
        public boolean isEnd() {
           return plateau.isEnd();
        }

        @Override
        public ArrayList<EquipeIHM> getEquipesIHM() {
            return plateau.getEquipesIHM();
        }

        @Override
        public ArrayList<JoueurIHM> getJoueursIHM() {
            return plateau.getJoueursIHM();
        }

        @Override
        public JoueurIHM getJoueurCourantIHM() {
            return plateau.getJoueurCourantIHM();
        }

    @Override
    public Boolean isEquipeMode() {
        return plateau.isEquipeMode();
    }
}
