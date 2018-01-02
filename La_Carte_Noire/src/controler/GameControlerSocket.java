package controler;

import model.PlateauSocketServeur;
import model.PlateauSocketClient;
import model.PlateauSocket;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import model.AbstractCarteIHM;
import model.Coord;
import model.Couleur;
import model.JoueurIHM;

public class GameControlerSocket extends Observable implements InterfaceControler, Observer {
    private PlateauSocket plateau;
    private String mode;

    public GameControlerSocket(String pseudo, String IP, String mode) throws IOException {
        this.mode = mode;
        if(mode.equals("Serveur")){
            this.plateau = new PlateauSocketServeur(pseudo,null);
        }
        else if(mode.equals("Client")){
            this.plateau = new PlateauSocketClient(pseudo,IP);
        }
        this.plateau.addObserver((Observer) this);
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
        this.notifyObservers("controler_cree"); 
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

    @Override
    public void update(Observable o, Object arg) {
       System.out.println("réceptionné depuis controller :" + (String)arg);
       this.notifyObservers(arg);
    }
    
    public void updateSocketState() throws IOException, InterruptedException{
        plateau.envoyerUpdate();
        plateau.receptionerUpdate();
    }
}
