package model;

import java.awt.Color;
import java.util.ArrayList;

public class Equipe {
    private int score;
    private ArrayList<Joueur> joueurs;
    private Color couleur;
    
    public Equipe(Color couleur){
        this.score = 0;
        this.couleur = couleur;
        this.joueurs = new ArrayList<Joueur>();
    }
    
    public Color getCouleur(){
        return couleur;
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
