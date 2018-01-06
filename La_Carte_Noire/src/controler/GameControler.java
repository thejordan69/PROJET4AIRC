package controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observer;
import javax.swing.Timer;
import model.EquipeIHM;
import model.Plateau;

public class GameControler extends AbstractGameControler {

    public GameControler(HashMap<String,Integer> mapJoueurs, String mode) {
        super();
        this.plateau = new Plateau(mapJoueurs,mode);
        this.notifyObservers(); 
    }

    @Override
    public void addObserver(Observer o){
            super.addObserver(o);
            this.notifyObservers(); 
    }

    @Override
    public ArrayList<EquipeIHM> getEquipesIHM() {
        return plateau.getEquipesIHM();
    }

    @Override
    public String getGameMode() {
        return plateau.getGameMode();
    }

    @Override
    public void moveIA() {
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
