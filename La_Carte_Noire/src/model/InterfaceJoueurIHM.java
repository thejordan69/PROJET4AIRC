package model;

import java.util.HashMap;

public interface InterfaceJoueurIHM {
    
    public String getPseudo();
    
    public String getScore();
    
    public Equipe getEquipe(); 
    
    public HashMap<Couleur, Integer> getNombreJetons();
    
    public HashMap<Couleur, Integer> getNombreCartes();
}
