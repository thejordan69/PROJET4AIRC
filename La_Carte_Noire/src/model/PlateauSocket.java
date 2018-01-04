package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;
import tools.Score;

public abstract class PlateauSocket extends Observable implements InterfacePlateauSocket {
    protected HashMap<Couleur,Jeton> listeJetons;
    protected ArrayList<AbstractCarte> listeCartes;
    protected ArrayList<Joueur> listeJoueurs;
    protected Joueur joueurCourant;
    protected CarteNoire carteNoire = null;
    protected String message, pseudo, IP;
    protected Socket socket;
    protected ObjectOutputStream sortie;
    protected ObjectInputStream entree;
    
    public PlateauSocket(String pseudo, String IP){
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
    
    public abstract void closeSocket();
    
    public void envoyerUpdate() throws IOException, InterruptedException{
        ArrayList<Object> objets = new ArrayList<Object>();
        objets.add(listeJoueurs);
        objets.add(listeCartes);
        objets.add(carteNoire);
        objets.add(joueurCourant);
        objets.add(message);
        Thread envoi = new Thread(new EnvoiSocket(socket,objets));
        envoi.start();
        envoi.join(); 
    }
    
    public void receptionerUpdate() throws IOException, InterruptedException{
        ArrayList<Object> objets = new ArrayList<Object>();
        Thread reception = new Thread(new ReceptionSocket(socket,objets));
        reception.start();
        reception.join();
       
        listeJoueurs = (ArrayList<Joueur>) objets.get(0);
        listeCartes = (ArrayList<AbstractCarte>) objets.get(1);
        carteNoire = (CarteNoire) objets.get(2);
        joueurCourant = (Joueur) objets.get(3);
        message = (String) objets.get(4);
        this.notifyObservers();
    }
    
    abstract void initialiserPartie();

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
    
    @Override
    public void notifyObservers(Object arg) {
        super.setChanged();
        super.notifyObservers(arg); 
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
            try {
                envoyerUpdate();
            } catch (IOException | InterruptedException ex) {
                Logger.getLogger(PlateauSocket.class.getName()).log(Level.SEVERE, null, ex);
            }
       }
       
       return true;
    }

    @Override
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
    
    @Override
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
    @Override
    public ArrayList<AbstractCarteIHM> getListeCartesIHM(){
        ArrayList<AbstractCarteIHM> listeCopie = new ArrayList<AbstractCarteIHM>();
        
        for(AbstractCarte carte : listeCartes){
            listeCopie.add(new AbstractCarteIHM(carte));
        }
        
        return listeCopie;
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
    
    @Override
    public String getMessage(){
        return this.message;
    }
    
    //permet de retourner la référence du vrai jeton possédé par le joueur
    private Jeton findJeton(Joueur joueur, Couleur couleur){
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
