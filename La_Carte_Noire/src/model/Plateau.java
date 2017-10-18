package model;

import java.util.ArrayList;
import java.util.Observable;

public class Plateau extends Observable implements InterfacePlateau {
    ArrayList<AbstractCarte> listeCartes;
    ArrayList<Joueur> listeJoueurs;
    Joueur joueurCourant;
    CarteNoire carteNoire = null;
    
    public Plateau(ArrayList<Joueur> joueurs){
        //création de la collection de cartes du pateau
        this.listeCartes = creerListeCartes();
        //initialisation de la liste des joueurs et fixation du premier joueur à jouer
        this.listeJoueurs = joueurs;
        this.joueurCourant = listeJoueurs.get(0);
    } 

    @Override
    public Couleur getCarteCouleur(int x, int y) {
        AbstractCarte carte = getCarte(x,y);
        if(carte != null){
            return carte.getCouleur();
        }
        return null;
    }

    @Override
    public boolean move(int xInit, int yInit, int xFinal, int yFinal) {
        Couleur choixCouleur;
        AbstractCarte carte;
        int min, max;
        
        //récupération de la couleur de la carte choisie
        choixCouleur = this.getCarteCouleur(xFinal, yFinal);
        //si déplacement en horizontal
        if (yInit == yFinal){
            min = Math.min(xInit, xFinal);
            max = Math.max(xInit, xFinal);
            for(int i=min;i<=max;i++){
                carte = getCarte(i,yInit);
                if(carte != null && carte.getCouleur() == choixCouleur){
                    gagnerCarte((Carte)carte);
                }
            }  
        }
        //si déplacement vertical
        else if (xInit == xFinal){
            min = Math.min(yInit, yFinal);
            max = Math.max(yInit, yFinal);
            for(int i=min;i<=max;i++){
                carte = getCarte(xInit,i);
                if(carte != null && carte.getCouleur() == choixCouleur){
                    gagnerCarte((Carte)carte);
                }
            }  
        }
        switchJoueur();
        return carteNoire.move(xFinal, yFinal);
    }

    //méthode qui est appellé lors d'un gain d'une carte
    private void gagnerCarte(Carte carte){
        joueurCourant.incrémenterCarte(carte.getCouleur());
        if(joueurCourant.getEquipe() != null){
            joueurCourant.getEquipe().incrementerScore();
        }
        miseAjourJeton(carte.getCouleur());
        carte.eliminer();
    }
    
    //méthode qui permet de tester si la partie est terminée
    @Override
    public boolean isEnd() {
        int i,x,y;
        x = carteNoire.getX();
        y = carteNoire.getY();
        
        for(i=0;i<6;i++){
            //test en horizontal
            if(isCarteHere(i,y) && getCarteCouleur(i,y) != Couleur.noire){
                return false;
            }
            //test en vertical
            if(isCarteHere(x,i) && getCarteCouleur(x,i) != Couleur.noire){
                return false;
            }
        }
        
       return true;
    }

    @Override
    public boolean isMoveOK(int xInit, int yInit, int xFinal, int yFinal) {
        boolean resultat = true;
        
        //si il n'y a pas de carte aux coordonées finales
        if (!isCarteHere(xFinal, yFinal)){
                System.out.println("Il n'y a pas de carte à cette position");
                resultat = false;
        }
        //si le déplacement de la carte noire n'est pas correct
        if (!carteNoire.isMoveOk(xFinal, yFinal)){
            System.out.println("Le déplacement de la carte noire n'est pas correct");
            resultat = false;
        }
        
        return resultat;
    }
    
    private boolean isCarteHere(int x, int y){
        if(getCarte(x, y) != null){
            return true;
        }
        return false;
    }
    
    private AbstractCarte getCarte(int x, int y){
        for(AbstractCarte carte : listeCartes){
            if(carte.getX() == x && carte.getY() == y){
                return carte;
            }
        }
        return null;
    }

    public Joueur getJoueurCourant(){
        return joueurCourant;
    }
    
    public ArrayList<Joueur> getListeJoueurs(){
        ArrayList<Joueur> listeCopie = new ArrayList();
        
        for(Joueur tmp : listeJoueurs){
            listeCopie.add((Joueur) tmp);
        }
        
        return listeCopie;
    }
    
    //méthode qui permet de renvoyer la liste des cartes qui vont être placées sur le damier
    private ArrayList<AbstractCarte> creerListeCartes(){        
        int i, random;
        Coord coords;
        AbstractCarte carte;
        ArrayList<AbstractCarte> listeCartes = new ArrayList<>();
        //création de deux tableaux permettant d'associer la couleur des cartes avec leur nombre respectif
        Couleur couleurs[] = {Couleur.bleue,Couleur.verte,Couleur.rouge,Couleur.rose,Couleur.jaune,Couleur.cyan,Couleur.orange,Couleur.noire};
	int nombres[] = {8,7,6,5,4,3,2,1};
        
        //boucle qui permet de créer les 36 cartes
        for (i = 0; i < 36; i++) {
            random = (int) (Math.random()*(8));
            while (nombres[random]<=0) {
                    random = (int) (Math.random()*(8));
            }
            //calcul des coordonnées à partir de l'index sur le plateau
            coords = new Coord(i%6, i/6);
            if(couleurs[random].equals(Couleur.noire)){
                carte = new CarteNoire(couleurs[random],coords);
                carteNoire = (CarteNoire) carte;
            }
            else{
                 carte = new Carte(couleurs[random],coords);
            }
            //décrémentation du nombre de carte de la couleur sélectionnée
            nombres[random]--;
            listeCartes.add(carte);
        }
        return listeCartes;
    }
    
    //méthode qui permet de renvoyer une copie des cartes créées
    public ArrayList<AbstractCarte> getListeCartes(){
        ArrayList<AbstractCarte> listeCopie = new ArrayList();
        
        for(AbstractCarte carte : listeCartes){
            listeCopie.add(carte);
        }
        
        return listeCopie;
    }
    
    private void switchJoueur(){
        int indexCourant = listeJoueurs.indexOf(joueurCourant);
        int nbJoueurs = listeJoueurs.size();
        
        if(indexCourant == (nbJoueurs-1)){
            joueurCourant = listeJoueurs.get(0);
        }
        else{
            joueurCourant = listeJoueurs.get(indexCourant+1);
        }
        System.out.println("C'est le tour de " + joueurCourant.getPseudo() + " avec un score de " + joueurCourant.getScore());
    }
    
    private void miseAjourJeton(Couleur couleur){
        int max = 0;
        Joueur meilleur = null;
        
        for(Joueur tmp : listeJoueurs){
            //récupère le joueur ayant le jeton de la couleur spécifiée ainsi que son nombre de carte pour cette couleur
            if((tmp.getNombreJetons().get(couleur) == 1) && !(tmp.getPseudo().equals(joueurCourant.getPseudo()))){
                meilleur = tmp;
                max = tmp.getNombreCartes().get(couleur);
            }
        }

        if(meilleur != null){
            //si le joueur courant a plus de carte que le meilleur actuel
            if(joueurCourant.getNombreCartes().get(couleur) >= max){
                joueurCourant.gagnerJeton(couleur);
                meilleur.perdreJeton(couleur);
                meilleur = null;
                System.out.println("Le joueur " + joueurCourant.getPseudo() + " a gagné le jeton " + couleur.toString());
            }
        }
        //sinon c'est le premier joueur à gagner le jeton
        else{
            joueurCourant.gagnerJeton(couleur);
            System.out.println("Le joueur " + joueurCourant.getPseudo() + " a gagné le jeton " + couleur.toString());
        }
    }
}
