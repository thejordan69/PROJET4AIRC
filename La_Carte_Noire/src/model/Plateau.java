package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import tools.Score;

public class Plateau extends AbstractPlateau implements InterfacePlateau {
    private Equipe equipe1, equipe2;
    private String modeJeu;
    
    public Plateau(HashMap<String,Integer> mapJoueurs, String mode){
        super();
        //création de la collection de cartes du pateau
        this.listeCartes = creerListeCartes();
        this.modeJeu = mode;
        initJetons();
        initJoueurs(mapJoueurs);
    } 

    //méthode qui permet d'initialiser les joueurs ainsi que leur potentielle équipe
    private void initJoueurs(HashMap<String,Integer> mapJoueurs){
        Joueur joueur;
        
        listeJoueurs = new ArrayList<Joueur>();      
        //check si le mode par équipe est activé
        if(modeJeu.equals("equipe")){
            equipe1 = new Equipe(Color.RED, "Rouge");
            equipe2 = new Equipe(Color.BLUE, "Bleue");
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
        }
        //le mode par équipe est désactivé (mode solo ou IA)
        else{
            equipe1 = null;
            equipe2 = null;
            for(Map.Entry<String,Integer> e : mapJoueurs.entrySet()){
                joueur = new Joueur(e.getKey());
                listeJoueurs.add(joueur);
            }
            if(modeJeu.equals("IA")){
                joueur = new Joueur("IA");
                listeJoueurs.add(joueur);
            }
            joueurCourant = listeJoueurs.get(0);
            message = "Début de la partie, c'est à " + joueurCourant.getPseudo() + " de commencer !";
        }      
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
        this.message = "C'est le tour de " + joueurCourant.getPseudo() + " avec un score de " + joueurCourant.getScore();
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
        miseAjourPion(carte.getCouleur());
        carte.eliminer();
    }
    
    private void miseAjourPion(Couleur couleur){
        int max = 0;
        Joueur meilleur = null;
                
        for(Joueur tmp : listeJoueurs){
            //récupère le joueur ayant le jeton de la couleur spécifiée ainsi que son nombre de carte pour cette couleur
            if((tmp.getListeJetons().contains(listeJetons.get(couleur))) && !(tmp.getPseudo().equals(joueurCourant.getPseudo()))){
                meilleur = tmp;
                max = tmp.getNombreCartes().get(couleur);
            }
        }

        if(meilleur != null){
            //si le joueur courant a plus de carte que le meilleur actuel
            if(joueurCourant.getNombreCartes().get(couleur) >= max){
                joueurCourant.gagnerJeton(listeJetons.get(couleur));
                meilleur.perdreJeton(listeJetons.get(couleur));
                meilleur = null;
                this.message = "Le joueur " + joueurCourant.getPseudo() + " a gagné le jeton " + couleur.toString();
            }
        }
        //sinon c'est le premier joueur à gagner le jeton
        else if ((meilleur == null) && !(joueurCourant.getListeJetons().contains(listeJetons.get(couleur)))){
            joueurCourant.gagnerJeton(listeJetons.get(couleur));
            this.message = "Le joueur " + joueurCourant.getPseudo() + " a gagné le jeton " + couleur.toString();
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
    public String getGameMode() {
        return this.modeJeu;
    }

    @Override
    public String getGagnant() {
        //mode par équipe
        if(getGameMode().equals("equipe")){
            if(equipe1.getNbJetons() > equipe2.getNbJetons()){
                return equipe1.getNom();
            }
            else if(equipe1.getNbJetons() < equipe2.getNbJetons()){
                return equipe2.getNom();
            }
            else{
                return getJoueurGagant().getEquipe().getNom();    
            } 
        }
        //mode chacun pour soit
        else{
            return getJoueurGagant().getPseudo();
        }
    }
    
    private Joueur getJoueurGagant(){
        int nb, importanceMax, max = -1;
        ArrayList<Joueur> liste = new ArrayList<Joueur>();
        Joueur gagnantFinal = null, meilleur;
        
        meilleur = listeJoueurs.get(0);

        for(Joueur tmp : listeJoueurs){
            nb = tmp.getListeJetons().size();
            if(nb == max){
                liste.add(tmp);
            }
            else if(nb > max){
                max = nb;
                meilleur = tmp;
                liste.clear();
            }
        }

        //s'il y a qu'un seul gagnant
        if(liste.isEmpty()){
            gagnantFinal = meilleur;
        }
        //si plusieurs joueurs ont le même nombre de jetons
        else{
            importanceMax = 0;
            liste.add(meilleur);
            gagnantFinal = meilleur;
            
            for(Joueur tmp : liste){
                ArrayList<Jeton> jetons = tmp.getListeJetons();
                for(Jeton current : jetons){
                    if(current.getImportance() > importanceMax){
                        importanceMax = current.getImportance();
                        gagnantFinal = tmp;
                    }
                }
            }
        }
        return gagnantFinal;
    }

    @Override
    public void saveScores() {
        HashMap<String,Integer> map = new HashMap<String,Integer>();
        
        //création de la hashmap associant les joueurs à leur score à partir de la liste des joueurs
        //si mode de jeu en IA, on met uniquement le joueur dans la liste
        if(modeJeu.equals("IA")){
            map.put(listeJoueurs.get(0).getPseudo(), listeJoueurs.get(0).getScore());
        }
        else{
            for(Joueur tmp : listeJoueurs){
                map.put(tmp.getPseudo(),tmp.getScore());
            }
        }
        Score.writeScores(map);
    }

    @Override
    public void moveIA() {
        ArrayList<AbstractCarte> cartes = new ArrayList<AbstractCarte>();
        int i,j, xNoire, yNoire, randomNum;
        AbstractCarte carteChoisie;
        
        xNoire = carteNoire.getX();
        yNoire = carteNoire.getY();
        
        //génération de la liste de cartes pouvant être prises
        for(i=0;i<6;i++){
            //analyse de toutes les carte sur la même ligne que celle de la carte noire
            if(isCarteHere(i,yNoire) && (getCarteCouleur(i,yNoire) != Couleur.noire)){
                cartes.add(getCarte(i,yNoire));
            }
            //analyse de toutes les carte sur la même colonne que celle de la carte noire
            if(isCarteHere(xNoire,i) && (getCarteCouleur(xNoire,i) != Couleur.noire)){
                cartes.add(getCarte(xNoire,i));
            } 
        }
        
        //génération d'un nombre aléatoire entre 0 et les nombre de carte disponibles
        randomNum = ThreadLocalRandom.current().nextInt(0,cartes.size());
        carteChoisie = cartes.get(randomNum);
        move(xNoire,yNoire,carteChoisie.getX(),carteChoisie.getY());
    }
}