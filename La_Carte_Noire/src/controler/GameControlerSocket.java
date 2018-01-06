package controler;

import model.PlateauSocketServeur;
import model.PlateauSocketClient;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class GameControlerSocket extends AbstractGameControler implements Observer {
    private String mode;

    public GameControlerSocket(String pseudo, String IP, String mode) throws IOException {
        super();
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
    public void addObserver(Observer o){
        super.addObserver(o);
        this.notifyObservers("controler_cree"); 
    }

    @Override
    public void update(Observable o, Object arg) {
       this.notifyObservers(arg);
    }
    
    @Override
    public void updateSocketState() {
        plateau.envoyerUpdate();
        plateau.receptionerUpdate();
    }
    
    @Override
    public void closeSocket(){
        plateau.closeSocket();
    }
}
