package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;
import tools.Score;

public abstract class AbstractPlateauSocket extends AbstractPlateau implements InterfacePlateauSocket {
    String message, pseudo, IP;
    Socket socket;
    ObjectOutputStream sortie;
    ObjectInputStream entree;
    
    public AbstractPlateauSocket(String pseudo, String IP){
        super();
        this.pseudo = pseudo;    
        this.IP = IP;
        //lancement de l'initialisation au bout de 700ms, le temps que le controler s'initialise
        ActionListener traitement = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                initSocket(IP);
            }
        };
        Timer timer = new Timer(700,traitement);
        timer.setRepeats(false);
        timer.start();
    }
    
    abstract void initSocket(String IP);
    
    @Override
    public abstract void closeSocket();
    
    @Override
    public void envoyerUpdate() {
        ArrayList<Object> objets = new ArrayList<Object>();
        objets.add(listeJoueurs);
        objets.add(listeCartes);
        objets.add(carteNoire);
        objets.add(joueurCourant);
        objets.add(message);
        Thread envoi;
        envoi = null;
        try {
            envoi = new Thread(new EnvoiSocket(socket,objets));
            envoi.start();
            envoi.join(); 
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(AbstractPlateauSocket.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    
    @Override
    public void receptionerUpdate() {
        ArrayList<Object> objets = new ArrayList<Object>();
        Thread reception = null;
        try {
            reception = new Thread(new ReceptionSocket(socket,objets));
            reception.start();
            reception.join();
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(AbstractPlateauSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        listeJoueurs = (ArrayList<Joueur>) objets.get(0);
        listeCartes = (ArrayList<AbstractCarte>) objets.get(1);
        carteNoire = (CarteNoire) objets.get(2);
        joueurCourant = (Joueur) objets.get(3);
        message = (String) objets.get(4);
        this.notifyObservers();
    }
    
    abstract void initialiserPartie();
    
    @Override
    public void notifyObservers(Object arg) {
        super.setChanged();
        super.notifyObservers(arg); 
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
        //permet d'avertir au deuxième joueur que la partie est terminée 
        if(true){
            envoyerUpdate();
       }
       
       return true;
    }

    protected void switchJoueur(){
        if(joueurCourant == listeJoueurs.get(0)){
            joueurCourant = listeJoueurs.get(1);
        }
        else{
            joueurCourant = listeJoueurs.get(0);
        }
        this.message = "C'est le tour de " + joueurCourant.getPseudo() + " avec un score de " + joueurCourant.getScore();
    }
    
    protected void miseAjourPion(Couleur couleur){
        Joueur autre;
        boolean possedeJeton = false;
        
        if(joueurCourant == listeJoueurs.get(0)){
            autre = listeJoueurs.get(1);
        }
        else{
            autre = listeJoueurs.get(0);
        }

        //test pour savoir si le joueur courant possède le jeton
        for(Jeton tmp : joueurCourant.getListeJetons()){
            if(tmp.getCouleur() == couleur){
                possedeJeton = true;
                break;
            }
        }
        
        if(!possedeJeton){
            if(joueurCourant.getNombreCartes().get(couleur) >= autre.getNombreCartes().get(couleur)){
                joueurCourant.gagnerJeton(listeJetons.get(couleur));
            }
            //test pour savoir si l'autre joueur possède le jeton
            possedeJeton = false;
            for(Jeton tmp : autre.getListeJetons()){
                if(tmp.getCouleur() == couleur){
                    possedeJeton = true;
                    break;
                }
            }
            if(possedeJeton){
                autre.perdreJeton(findJeton(autre,couleur));
            }  
        }
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
    protected void gagnerCarte(Carte carte){
        joueurCourant.incrémenterCarte(carte.getCouleur());
        miseAjourPion(carte.getCouleur());
        carte.eliminer();
    }

    @Override
    public String getGagnant(){
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
        return gagnantFinal.getPseudo();
    }

    @Override
    public void saveScores() {
        HashMap<String,Integer> map = new HashMap<String,Integer>();
        
        //création de la hashmap associant les joueurs à leur score à partir de la liste des joueurs       
        for(Joueur tmp : listeJoueurs){
            map.put(tmp.getPseudo(),tmp.getScore());
        }
        Score.writeScores(map);
    }
    
    //permet de retourner la référence du vrai jeton possédé par le joueur
    protected Jeton findJeton(Joueur joueur, Couleur couleur){
        Jeton res = null;
        for(Jeton tmp : joueur.getListeJetons()){
            if(tmp.getCouleur() == couleur){
                res = tmp;
                break;
            }
        }
        return res;
    }
}
