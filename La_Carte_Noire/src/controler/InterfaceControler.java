package controler;

import java.util.ArrayList;
import model.AbstractCarteIHM;
import model.Coord;
import model.Couleur;
import model.JoueurIHM;

public interface InterfaceControler {

    public boolean move(Coord initCoord, Coord finalCoord);

    public Couleur getCarteCouleur(Coord coords);

    public boolean isEnd();

    public ArrayList<AbstractCarteIHM> getListeCartesIHM();

    public ArrayList<JoueurIHM> getJoueursIHM();

    public JoueurIHM getJoueurCourantIHM();

    public String getGagnant();

    public void saveScores();
    
    public String getMessage();
}
