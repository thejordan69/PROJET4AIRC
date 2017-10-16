package launcher;

import controler.GameControler;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Observer;
import model.Joueur;
import model.Plateau;
import view.MenuGUI;
import view.PlateauGUI;

public class LauncherGUI {
    private static PlateauGUI plateauGUI;
    private static GameControler controler;
    private static Plateau plateau;
    private static Dimension dim;
    
    public static void main(String[] args) {
        MenuGUI menu = new MenuGUI();           
    }
        
    //permet de créer une partie
    public static void creerPartie(){
        ArrayList listeJoueurs = new ArrayList<Joueur>();
        listeJoueurs.add(new Joueur("Damien"));
        listeJoueurs.add(new Joueur("Marion"));
        plateau = new Plateau(listeJoueurs);
        controler = new GameControler(plateau);
        dim = new Dimension(800,800);
        plateauGUI = new PlateauGUI(controler, dim);
        controler.addObserver((Observer) plateauGUI);
    }
}
