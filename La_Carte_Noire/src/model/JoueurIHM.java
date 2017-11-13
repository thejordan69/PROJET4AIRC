package model;

import java.util.ArrayList;
import java.util.HashMap;

public class JoueurIHM implements InterfaceJoueurIHM {
    private Joueur joueur;
    
    public JoueurIHM(Joueur joueur){
        this.joueur = joueur;
    }

    @Override
    public String getPseudo() {
        return joueur.getPseudo();
    }

    @Override
    public String getScore() {
        return String.valueOf(joueur.getScore());
    }

    @Override
    public Equipe getEquipe() {
        return joueur.getEquipe();
    }

    @Override
    public ArrayList<Jeton> getListeJetons() {
        return joueur.getListeJetons();
    }

    @Override
    public HashMap<Couleur, Integer> getNombreCartes() {
        return joueur.getNombreCartes();
    }
}
