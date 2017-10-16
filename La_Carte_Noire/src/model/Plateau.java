package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

public class Plateau extends Observable implements InterfacePlateau {
    HashMap<Coord,AbstractCarte> listeCartes;
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
        AbstractCarte carte;
        
        carte = (AbstractCarte) listeCartes.get(new Coord(x,y));
        return carte.getCouleur();
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
                carte = ((AbstractCarte) listeCartes.get(new Coord(i,yInit)));
                if(carte !=null && carte.getCouleur() == choixCouleur){
                    joueurCourant.incrémenterCarte(carte.getCouleur());
                    ((Carte) carte).eliminer();
                }
            }  
        }
        //si déplacement vertical
        else if (xInit == xFinal){
            min = Math.min(yInit, yFinal);
            max = Math.max(yInit, yFinal);
            for(int i=min;i<=max;i++){
                carte = (AbstractCarte) listeCartes.get(new Coord(xInit,i));
                if(carte != null && carte.getCouleur() == choixCouleur){
                    joueurCourant.incrémenterCarte(carte.getCouleur());
                    ((Carte) carte).eliminer();
                }
            }  
        }
        
        //suppression de la carte noire dans la hashmap et recréation de celle-ci avec les nouvelles coordonnées en clé
        listeCartes.remove(listeCartes.get(new Coord(xInit,yInit)));
        listeCartes.put(new Coord(xFinal, yFinal), carteNoire);
        switchJoueur();
        return carteNoire.move(xFinal, yFinal);
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
        return listeCartes.containsKey(new Coord(x,y));
    }

    @Override
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
    private HashMap<Coord,AbstractCarte> creerListeCartes(){        
        int i, random;
        Coord coords;
        AbstractCarte carte;
        HashMap<Coord,AbstractCarte> listeCartes = new HashMap<>();
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
            listeCartes.put(coords, carte);
        }
        return listeCartes;
    }
    
    //méthode qui permet de renvoyer une copie des cartes créées
    @Override
    public ArrayList<AbstractCarte> getListeCartes(){
        ArrayList<AbstractCarte> listeCopie = new ArrayList();
        
        for(Map.Entry entry : listeCartes.entrySet()){
            listeCopie.add((AbstractCarte) entry.getValue());
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
        System.out.println("C'est le tour de " + joueurCourant.getPseudo() + " qui a un score de " + joueurCourant.getScore());
    }
}
