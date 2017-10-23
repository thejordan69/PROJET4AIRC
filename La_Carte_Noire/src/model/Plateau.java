package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

public class Plateau extends Observable implements InterfacePlateau {
    private ArrayList<AbstractCarte> listeCartes;
    private ArrayList<Joueur> listeJoueurs;
    private Joueur joueurCourant;
    private CarteNoire carteNoire = null;
    private Equipe equipe1, equipe2;
    
    public Plateau(HashMap<String,Integer> mapJoueurs){
        //création de la collection de cartes du pateau
        this.listeCartes = creerListeCartes();
        initJoueurs(mapJoueurs);
    } 

    //méthode qui permet d'initialiser les joueurs ainsi que leur potentielle équipe
    private void initJoueurs(HashMap<String,Integer> mapJoueurs){
        Joueur joueur;
        
        listeJoueurs = new ArrayList<Joueur>();      
        //check si le mode par équipe est activé
        if(mapJoueurs.containsValue(1)){
            equipe1 = new Equipe(Color.RED);
            equipe2 = new Equipe(Color.BLUE);
            for(Map.Entry<String,Integer> e : mapJoueurs.entrySet()){
                joueur = new Joueur(e.getKey());
                if(e.getValue() == 1){
                    joueur.intégrerEquipe(equipe1);
                    equipe1.intégrerJoueur(joueur);
                    listeJoueurs.add(joueur);
                }
                else{
                    joueur.intégrerEquipe(equipe2);
                    equipe2.intégrerJoueur(joueur);
                    listeJoueurs.add(joueur);
                }
            } 
            joueurCourant = equipe1.getJoueurs().get(0);
            System.out.println(joueurCourant.getPseudo());
        }
        //le mode par équipe est désactivé
        else{
            equipe1 = null;
            equipe2 = null;
            for(Map.Entry<String,Integer> e : mapJoueurs.entrySet()){
                joueur = new Joueur(e.getKey());
                listeJoueurs.add(joueur);
            }
            joueurCourant = listeJoueurs.get(0);
        }   
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
        joueurCourant.incrémenterScores();
        miseAjourPion(carte.getCouleur());
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
    @Override
    public ArrayList<AbstractCarteIHM> getListeCartesIHM(){
        ArrayList<AbstractCarteIHM> listeCopie = new ArrayList<AbstractCarteIHM>();
        
        for(AbstractCarte carte : listeCartes){
            listeCopie.add(new AbstractCarteIHM(carte));
        }
        
        return listeCopie;
    }
    
    private void switchJoueur(){
        Equipe equipeCourante = joueurCourant.getEquipe();
        //si mode de jeu pas par équipe
        if(equipeCourante == null){
            int indexCourant = listeJoueurs.indexOf(joueurCourant);
            int nbJoueurs = listeJoueurs.size();
            if(indexCourant == (nbJoueurs-1)){
                joueurCourant = listeJoueurs.get(0);
            }
            else{
                joueurCourant = listeJoueurs.get(indexCourant+1);
            }
        }
        //mode de jeu par équipe
        else{
            //si équipe1, on prend dans l'équipe2 au même index
            if(equipeCourante == equipe1){
                int indexCourant = equipeCourante.getJoueurs().indexOf(joueurCourant);
                joueurCourant = equipe2.getJoueurs().get(indexCourant);
            }
            //si équipe2, on check que c'est pas la fin de l'équipe1, et on prend le suivant
            else{
                int indexCourant = equipeCourante.getJoueurs().indexOf(joueurCourant);
                int nbJoueurs = equipeCourante.getJoueurs().size();
                if(indexCourant == (nbJoueurs-1)){
                    joueurCourant = equipe1.getJoueurs().get(0);
                }
                else{
                    joueurCourant = equipe1.getJoueurs().get(indexCourant+1);
                }       
            }  
        }
        System.out.println("C'est le tour de " + joueurCourant.getPseudo() + " avec un score de " + joueurCourant.getScore() + " et un score d'équipe de " + joueurCourant.getEquipe().getScore());
    }
    
    private void miseAjourPion(Couleur couleur){
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

    @Override
    public ArrayList<EquipeIHM> getEquipesIHM() {
        ArrayList<EquipeIHM> equipes = null;
        
        if(equipe1 != null){
            equipes = new ArrayList<EquipeIHM>();
            equipes.add(new EquipeIHM(equipe1));
            equipes.add(new EquipeIHM(equipe2)); 
        }
        
        return equipes;
    }

    @Override
    public ArrayList<JoueurIHM> getJoueursIHM() {
        ArrayList<JoueurIHM> joueurs = new ArrayList<JoueurIHM>();
        for(Joueur tmp : listeJoueurs){
            joueurs.add(new JoueurIHM(tmp));
        }
        
        return joueurs;
    }

    @Override
    public JoueurIHM getJoueurCourantIHM() {
        return new JoueurIHM(joueurCourant);
    }

    @Override
    public Boolean isEquipeMode() {
        if(equipe1 != null){
            return true;
        }
        else{
            return false;
        }
    }
}
