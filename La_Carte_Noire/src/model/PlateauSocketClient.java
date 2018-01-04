package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;
import view.PlateauGUISocket;

public class PlateauSocketClient extends PlateauSocket {
    
    public PlateauSocketClient(String pseudo, String IP) throws IOException {
        super(pseudo,IP);
    } 
    
    //méthode qui permet d'initialiser la socket du serveur
    @Override
    void initSocket(String IP) {
        try {
            socket = new Socket(IP,1234);
            initialiserPartie();
        }catch(UnknownHostException e) {
            e.printStackTrace();
            this.notifyObservers("no_socket");
        }catch(IOException e) {
            e.printStackTrace();
            this.notifyObservers("no_socket");
        }
        //permet de fermer les sockets lors de la fermeture de l'appli
        Runtime.getRuntime().addShutdownHook(new Thread(){public void run(){
            closeSocket();
        }});
    }
    
    @Override
     public void closeSocket(){
        try{
            socket.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    void initialiserPartie() {
        initJetons();
        try {
            //envoi du pseudo au serveur pour initialiser la partie
            envoyerPseudo();
            receptionerUpdate();
            //permet de mettre le client en attente du premier déplacement du serveur
            ActionListener traitement = new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    try {
                        receptionerUpdate();
                    } catch (IOException | InterruptedException ex) {
                        Logger.getLogger(PlateauGUISocket.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            };
            Timer timer = new Timer(1000,traitement);
            timer.setRepeats(false);
            timer.start(); 
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(PlateauSocketClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void envoyerPseudo() throws IOException, InterruptedException{
        ArrayList<Object> objets = new ArrayList<Object>();
        objets.add(pseudo);
        Thread envoi = new Thread(new EnvoiSocket(socket,objets));
        envoi.start();
        envoi.join();
    }
}