package model;

import java.util.ArrayList;
import java.util.HashMap;

public interface InterfaceJoueurIHM {
    
    public String getPseudo();
    
    public String getScore();
    
    public Equipe getEquipe(); 
    
    public ArrayList<Jeton> getListeJetons();
    
    public HashMap<Couleur, Integer> getNombreCartes();
}
