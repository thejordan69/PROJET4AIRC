 package model;

import java.util.HashMap;

public class Joueur {
    private String pseudo;
    private int score;
    private Equipe equipe;
    private HashMap<Couleur,Integer> nombreCartes;
    private HashMap<Couleur,Integer> nombreJetons;
    
    public Joueur(String pseudo){
        this.pseudo = pseudo;
        this.score = 0;
        this.nombreCartes = new HashMap<Couleur,Integer>();
        this.nombreJetons = new HashMap<Couleur,Integer>();
        nombreCartes.put(Couleur.bleue,0);
        nombreCartes.put(Couleur.cyan,0);
        nombreCartes.put(Couleur.jaune,0);
        nombreCartes.put(Couleur.orange,0);
        nombreCartes.put(Couleur.rose,0);
        nombreCartes.put(Couleur.rouge,0);
        nombreCartes.put(Couleur.verte,0);
        nombreJetons.put(Couleur.bleue,0);
        nombreJetons.put(Couleur.cyan,0);
        nombreJetons.put(Couleur.jaune,0);
        nombreJetons.put(Couleur.orange,0);
        nombreJetons.put(Couleur.rose,0);
        nombreJetons.put(Couleur.rouge,0);
        nombreJetons.put(Couleur.verte,0);
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
    
    public HashMap<Couleur, Integer> getNombreJetons() {
        return nombreJetons;
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
    
    public void gagnerJeton(Couleur couleur){
        nombreJetons.replace(couleur,1);
    }
    
    public void perdreJeton(Couleur couleur){
        nombreJetons.replace(couleur,0);
    }
    
    public void intégrerEquipe(Equipe equipe){
        this.equipe = equipe;
    }
}
