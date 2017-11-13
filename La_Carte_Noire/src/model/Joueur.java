 package model;

import java.util.ArrayList;
import java.util.HashMap;

public class Joueur {
    private String pseudo;
    private int score;
    private Equipe equipe;
    private HashMap<Couleur,Integer> nombreCartes;
    private ArrayList<Jeton> listeJetons;
    
    public Joueur(String pseudo){
        this.pseudo = pseudo;
        this.score = 0;
        this.nombreCartes = new HashMap<Couleur,Integer>();
        this.listeJetons = new ArrayList<Jeton>();
        nombreCartes.put(Couleur.bleue,0);
        nombreCartes.put(Couleur.cyan,0);
        nombreCartes.put(Couleur.jaune,0);
        nombreCartes.put(Couleur.orange,0);
        nombreCartes.put(Couleur.rose,0);
        nombreCartes.put(Couleur.rouge,0);
        nombreCartes.put(Couleur.verte,0);
    }

    public String getPseudo() {
        return pseudo;
    }

    public int getScore() {
        return score;
    }
    
    public Equipe getEquipe(){
        return equipe;
    }
    
    public HashMap<Couleur, Integer> getNombreCartes() {
        return nombreCartes;
    }
    
    public ArrayList<Jeton> getListeJetons() {
        return listeJetons;
    }
    
    public void incrémenterCarte(Couleur couleur){
        nombreCartes.replace(couleur, ((Integer) nombreCartes.get(couleur))+1);
    }
    
    public void incrémenterScores(){
        score++;
        if(this.getEquipe() != null){
            this.getEquipe().incrementerScore();
        }
    }
    
    public void gagnerJeton(Jeton jeton){
        listeJetons.add(jeton);
    }
    
    public void perdreJeton(Jeton jeton){
        listeJetons.remove(jeton);
    }
    
    public void intégrerEquipe(Equipe equipe){
        this.equipe = equipe;
    }
}
