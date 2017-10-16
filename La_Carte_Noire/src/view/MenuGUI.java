package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import launcher.LauncherGUI;
import view.PseudoGUI;

public class MenuGUI extends JFrame {
    
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
                getContentPane().setVisible(false);
                PseudoGUI pseudo = new PseudoGUI();
                pseudo.setVisible(true);
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
