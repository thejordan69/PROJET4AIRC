package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;

public abstract class AbstractPlateau extends Observable {
    ArrayList<AbstractCarte> listeCartes;
    HashMap<Couleur,Jeton> listeJetons;
    ArrayList<Joueur> listeJoueurs;
    Joueur joueurCourant;
    CarteNoire carteNoire = null;
    String message;

    public AbstractPlateau(){}
    
    //méthode qui permet d'initialiser les jetons
    protected void initJetons(){
        listeJetons = new HashMap<Couleur,Jeton>();
        listeJetons.put(Couleur.bleue,new Jeton(Couleur.bleue,8));
        listeJetons.put(Couleur.verte,new Jeton(Couleur.verte,7));
        listeJetons.put(Couleur.rouge,new Jeton(Couleur.rouge,6));
        listeJetons.put(Couleur.rose,new Jeton(Couleur.rose,5));
        listeJetons.put(Couleur.jaune,new Jeton(Couleur.jaune,4));
        listeJetons.put(Couleur.cyan,new Jeton(Couleur.cyan,3));
        listeJetons.put(Couleur.orange,new Jeton(Couleur.orange,2));
    }
    
    //méthode qui permet de tester si la partie est terminée
    public abstract boolean isEnd();
    
    public boolean isMoveOK(int xInit, int yInit, int xFinal, int yFinal) {
        boolean resultat = true;
        
        if((xInit == xFinal) && (yInit == yFinal)){
            this.message = "La position initiale est identique à la position finale";
            resultat = false;
        }
        //si il n'y a pas de carte aux coordonées finales
        else if(!isCarteHere(xFinal, yFinal)){
                this.message = "Il n'y a pas de carte à cette position";
                resultat = false;
        }
        //si le déplacement de la carte noire n'est pas correct
        else if(!carteNoire.isMoveOk(xFinal, yFinal)){
            this.message = "Le déplacement de la carte noire n'est pas correct";
            resultat = false;
        }
        
        return resultat;
    }
    
    protected boolean isCarteHere(int x, int y){
        if(getCarte(x, y) != null){
            return true;
        }
        return false;
    }
    
    protected AbstractCarte getCarte(int x, int y){
        for(AbstractCarte carte : listeCartes){
            if(carte.getX() == x && carte.getY() == y){
                return carte;
            }
        }
        return null;
    }
    
    public Couleur getCarteCouleur(int x, int y) {
        AbstractCarte carte = getCarte(x,y);
        if(carte != null){
            return carte.getCouleur();
        }
        return null;
    }
    
    //méthode qui permet de renvoyer la liste des cartes qui vont être placées sur le damier
    protected ArrayList<AbstractCarte> creerListeCartes(){        
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
    public ArrayList<AbstractCarteIHM> getListeCartesIHM(){
        ArrayList<AbstractCarteIHM> listeCopie = new ArrayList<AbstractCarteIHM>();
        
        for(AbstractCarte carte : listeCartes){
            listeCopie.add(new AbstractCarteIHM(carte));
        }
        
        return listeCopie;
    }
    
    public ArrayList<JoueurIHM> getJoueursIHM() {
        ArrayList<JoueurIHM> joueurs = new ArrayList<JoueurIHM>();
        for(Joueur tmp : listeJoueurs){
            joueurs.add(new JoueurIHM(tmp));
        }
        
        return joueurs;
    }
    
    public JoueurIHM getJoueurCourantIHM() {
        return new JoueurIHM(joueurCourant);
    }
    
    public String getMessage(){
        return this.message;
    }

    abstract public boolean move(int xInit, int yInit, int xFinal, int yFinal);

    abstract public String getGagnant();

    abstract public void saveScores();   

    public ArrayList<EquipeIHM> getEquipesIHM() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getGameMode() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void moveIA() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void envoyerUpdate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void receptionerUpdate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void closeSocket() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
