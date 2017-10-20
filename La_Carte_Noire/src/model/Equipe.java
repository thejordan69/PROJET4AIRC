package model;

import java.util.ArrayList;

public class Equipe {
    private int score;
    ArrayList<Joueur> joueurs;
    
    public Equipe(){
        this.score = 0;
        this.joueurs = new ArrayList<Joueur>();
    }
    
    public int getScore(){
        return score;
    }
    
    public ArrayList<Joueur> getJoueurs(){
        return joueurs;
    }
    
    public void incrementerScore(){
        score++;
    }

    public void intégrerJoueur(Joueur joueur){
        joueurs.add(joueur);
    }
}
