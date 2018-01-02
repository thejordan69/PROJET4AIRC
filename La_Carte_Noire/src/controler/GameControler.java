package controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import javax.swing.Timer;
import model.AbstractCarteIHM;
import model.Coord;
import model.Couleur;
import model.EquipeIHM;
import model.JoueurIHM;
import model.Plateau;

public class GameControler extends Observable implements InterfaceControler {
    private Plateau plateau;

    public GameControler(HashMap<String,Integer> mapJoueurs, String mode) {
        this.plateau = new Plateau(mapJoueurs,mode);
        this.notifyObservers(); 
    }

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
    public void addObserver(Observer o){
            super.addObserver(o);
            this.notifyObservers(); 
    }

    @Override
    public boolean isEnd() {
       return plateau.isEnd();
    }

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

    public String getGameMode() {
        return plateau.getGameMode();
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

    public void moveIA() throws InterruptedException {
        ActionListener traitement = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                plateau.moveIA();
                notifyObservers(null);
            }
        };
        Timer timer = new Timer(1500,traitement);
        timer.setRepeats(false);
        timer.start();
    }
}
