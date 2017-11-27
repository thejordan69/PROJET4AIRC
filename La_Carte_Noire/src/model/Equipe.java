package model;

import java.awt.Color;
import java.util.ArrayList;

public class Equipe {
    private int nbJetons;
    private ArrayList<Joueur> joueurs;
    private Color couleur;
    private String nom;
    
    public Equipe(Color couleur, String nom){
        this.nbJetons = 0;
        this.couleur = couleur;
        this.nom = nom;
        this.joueurs = new ArrayList<Joueur>();
    }
    
    public Color getCouleur(){
        return couleur;
    }
    
    public int getNbJetons(){
        return nbJetons;
    }
    
    public ArrayList<Joueur> getJoueurs(){
        return joueurs;
    }
    
    public String getNom(){
        return nom;
    }
    
    public void gagnerJeton(){
        nbJetons++;
    }
    
    public void perdreJeton(){
        nbJetons--;
    }

    public void intégrerJoueur(Joueur joueur){
        joueurs.add(joueur);
    }
}
