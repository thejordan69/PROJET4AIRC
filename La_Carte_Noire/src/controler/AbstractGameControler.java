package controler;

import java.util.ArrayList;
import java.util.Observable;
import model.AbstractCarteIHM;
import model.AbstractPlateau;
import model.Coord;
import model.Couleur;
import model.EquipeIHM;
import model.JoueurIHM;

public class AbstractGameControler extends Observable implements InterfaceControler {
    AbstractPlateau plateau;
    
    public AbstractGameControler(){}
    
    @Override
    public boolean move(Coord initCoord, Coord finalCoord) {
        boolean ret = false;

        ret = plateau.isMoveOK(initCoord.getX(), initCoord.getY(), finalCoord.getX(), finalCoord.getY());
        if(ret){
            ret = plateau.move(initCoord.getX(), initCoord.getY(), finalCoord.getX(), finalCoord.getY());
        }
        this.notifyObservers(); 
        return ret;
    }
    
    @Override
    public Couleur getCarteCouleur(Coord coords){
        return plateau.getCarteCouleur(coords.getX(),coords.getY());
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
    public boolean isEnd() {
       return plateau.isEnd();
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
    public String getGagnant() {
        return plateau.getGagnant();
    }
    
    @Override
    public void saveScores(){
        plateau.saveScores();
    }
    
    @Override
    public String getMessage(){
        return plateau.getMessage();
    }
    
    public ArrayList<EquipeIHM> getEquipesIHM() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getGameMode() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void moveIA(){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void updateSocketState(){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void closeSocket(){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
