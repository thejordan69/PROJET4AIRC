package model;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Joueur;

public class PlateauSocketServeur extends PlateauSocket {
    private ServerSocket socketServer;
    
    public PlateauSocketServeur(String pseudo, String IP) throws IOException {
        super(pseudo,IP);
        initSocket(null);
        initialiserPartie();
    } 

    //méthode qui permet d'initialiser la socket du serveur
    @Override
    void initSocket(String IP) {
        try {
            socketServer = new ServerSocket(1234);
            System.out.println("SERVEUR EN ATTENTE DU CLIENT");
            socket= socketServer.accept(); 
            System.out.println("CLIENT CONNECTE");
        }
        catch (IOException e) {
            e.printStackTrace();
            closeSocket();
        }
        //permet de fermer les sockets lors de la fermeture de l'appli
        Runtime.getRuntime().addShutdownHook(new Thread(){public void run(){
            closeSocket();
        }});
    }
    
    @Override
    void closeSocket(){
        try{
            socketServer.close();
            socket.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    void initialiserPartie() {
        listeJoueurs = new ArrayList<Joueur>();
        listeJoueurs.add(new Joueur(pseudo));
        try {
            //récupération du nom du deuxième joueur quand celui-ci sera connecté
            listeJoueurs.add(new Joueur(receptionerPseudo()));
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(PlateauSocketServeur.class.getName()).log(Level.SEVERE, null, ex);
        }
        joueurCourant = listeJoueurs.get(0);
        this.listeCartes = creerListeCartes();
        initJetons();
        message = "Début de la partie, c'est à " + joueurCourant.getPseudo() + " de commencer !";
        try {
            envoyerUpdate();
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(PlateauSocketServeur.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.notifyObservers();
    }
    
    private String receptionerPseudo() throws IOException, InterruptedException{
        ArrayList<Object> objets = new ArrayList<Object>();
        Thread reception = new Thread(new ReceptionSocket(socket,objets));
        reception.start();
        reception.join();
        return objets.get(0).toString();
    }
}