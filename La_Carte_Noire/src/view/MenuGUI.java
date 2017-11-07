package view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class MenuGUI extends JFrame {
    private PlateauGUI plateauGUI;
    private RegleGUI regleGUI;
    
    public MenuGUI(){
        //création de la JFrame
        this.setTitle("Menu");
        this.setSize(400, 500);
	this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);             
        
        //création des boutons
        JButton bouton_jouer = new JButton("Jouer");
        JButton bouton_regles = new JButton("Règles");
        JButton bouton_options = new JButton("Options");
        JButton bouton_scores = new JButton("Scores");
        //ajout des listeners aux boutons 
        bouton_jouer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                SwingUtilities.getWindowAncestor(getContentPane()).setVisible(false);
                HashMap<String, Integer> mapJoueurs = new HashMap();
                mapJoueurs.put("Damien",1);
                mapJoueurs.put("Marion",2);
                mapJoueurs.put("Jordan",2);
                mapJoueurs.put("Fred",1);
                Dimension dimPlateau = new Dimension(800,800);
                Dimension dimRecap = new Dimension(400,800);
                plateauGUI = new PlateauGUI(dimPlateau,dimRecap,mapJoueurs);
            }
        }); 
        bouton_regles.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                SwingUtilities.getWindowAncestor(getContentPane()).setVisible(false);
                regleGUI = new RegleGUI();
                regleGUI.setVisible(true);
            }
        }); 
        //ajout des boutons à la JFrame
        this.setLayout(new GridLayout(4, 1));
        this.add(bouton_jouer);
        this.add(bouton_regles);
        this.add(bouton_options);
        this.add(bouton_scores);  
        this.setVisible(true);
    }	   
}
