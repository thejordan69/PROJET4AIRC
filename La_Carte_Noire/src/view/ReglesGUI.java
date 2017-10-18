/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;


/**
 *
 * @author Frederic
 */
public class ReglesGUI extends JFrame{
    
    public ReglesGUI(){
        //création de la JFrame
        this.setTitle("La Carte Noire - Règles du jeu");
        this.setSize(500, 500);
	this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);             
        
        //création JPannel
        JPanel pannel = new JPanel();
        
        //création du texte
        JTextArea texte = new JTextArea();
        texte.setLineWrap(true);
        texte.setWrapStyleWord(true);
        texte.setEditable(false);
        texte.setVisible(true);
        texte.setColumns(40);
        texte.setRows(25);
        texte.setText("Nombre de joueurs : 2 à 4 joueurs\n\nBut du jeu : Avoir le plus de jeton(s) de couleur\n\nDéroulement du jeu :\n- Déplacez la carte noire verticalement ou horizontalement pour prendre une carte\n- Choisissez une carte à recouvrir, saisissez-vous-en ainsi que de toutes les cartes survolées de même couleur\n- A la fin de votre tour, si vous possédez un nombre de cartes d’une même couleur supérieur ou égal à celui de vos adversaires, prenez le jeton de couleur correspondant\n- A la fin de la partie, celui qui possède le plus de jeton(s) gagne, en cas d’égalité il s’agit du joueur qui a en outre le plus de cartes dans une même couleur\n\nFin du jeu : La partie est terminée lorsqu’il ne reste plus de cartes de couleur ou que la carte noire ne peut plus survoler de carte horizontalement ni verticalement\n\n\nJeu par équipe (4 joueurs obligatoire,  2 vs 2) :\nLe déroulement du jeu est identique à une partie classique, cependant à la fin de la partie, on additionne les jetons des deux joueurs de chaque équipe\n");
        
        //ajout du texte au JPannel
        pannel.add(texte);
        this.getContentPane().add(pannel);
        this.setVisible(true);
        
        //création du bouton retour
        JButton bouton_retour = new JButton("Retour");
        
        bouton_retour.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                
                //fermeture du Jpanel
                SwingUtilities.getWindowAncestor(getContentPane()).setVisible(false);
                
                //ouverture du menu principal MenuGUI
                MenuGUI menu = new MenuGUI();
                menu.setVisible(true);      
            }      
        }); 
         
        //affichage du bouton retour en bas du Jpannel
        pannel.setLayout(new BorderLayout());
        pannel.add(bouton_retour,BorderLayout.SOUTH);        
    }
    public static void main(String args[]) {
        ReglesGUI regle = new ReglesGUI();
        regle.setVisible(true);
    }
}
