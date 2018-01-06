package model;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PlateauSocketServeur extends AbstractPlateauSocket {
    private ServerSocket socketServer;
    
    public PlateauSocketServeur(String pseudo, String IP) throws IOException {
        super(pseudo,IP);
    } 

    //méthode qui permet d'initialiser la socket du serveur
    @Override
    void initSocket(String IP) {
        try {
            //socketServer = new ServerSocket(1234);
            socketServer = new ServerSocket(1234);
            
            this.notifyObservers("attente_client");
            final AbstractPlateauSocket param = this;
            Thread t = new Thread(new Runnable() {
                AbstractPlateauSocket plateau = param;
                public void run() { 
                    try { 
                        socket = socketServer.accept();
                        //ferme la socket serveur afin de ne conencter qu'un seul client à la fois
                        socketServer.close();
                    } catch (IOException ex) {
                        Logger.getLogger(PlateauSocketServeur.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    initialiserPartie();
                    //plateau.notifyObservers("client_connecté");  
                };
            });
            t.start();
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
    public void closeSocket(){
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
        this.notifyObservers("client_connecté");
        envoyerUpdate();
    }
    
    private String receptionerPseudo() throws IOException, InterruptedException{
        ArrayList<Object> objets = new ArrayList<Object>();
        Thread reception = new Thread(new ReceptionSocket(socket,objets));
        reception.start();
        reception.join();
        return objets.get(0).toString();
    }
}