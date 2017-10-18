package model;

import java.util.ArrayList;

public class Equipe {
    private int score;
    ArrayList<Joueur> joueurs;
    
    public Equipe(ArrayList<Joueur> joueurs){
        this.joueurs = joueurs;
        this.score = 0;
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
}
