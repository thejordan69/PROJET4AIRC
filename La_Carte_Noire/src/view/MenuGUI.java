package view;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;
import tools.ImagePanel;

public class MenuGUI extends JFrame {
    private JButton Bouton_About, Bouton_Jouer, Bouton_Options, Bouton_Scores;
    private JPanel panel;  
    
    public MenuGUI() {
        initComponents();
    }
                          
    private void initComponents() {
        this.setTitle("Menu");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setPreferredSize(new Dimension(965,620));
        
        panel = new ImagePanel("menu");
        panel.setPreferredSize(new Dimension(965,620));
        panel.setLayout(new AbsoluteLayout());
        
        Bouton_Jouer = new JButton();
        Bouton_Jouer.setIcon(new ImageIcon(getClass().getResource("/images/Bouton jouer_dark.png")));
        Bouton_Jouer.setBorder(null);
        Bouton_Jouer.setContentAreaFilled(false);
        Bouton_Jouer.setMargin(new Insets(0, 0, 0, 0));
        Bouton_Jouer.addActionListener(this::Bouton_JouerActionPerformed);
        panel.add(Bouton_Jouer, new AbsoluteConstraints(210, 300, -1, -1));

        Bouton_Scores = new JButton();
        Bouton_Scores.setIcon(new ImageIcon(getClass().getResource("/images/Bouton scores_dark.png"))); // NOI18N
        Bouton_Scores.setAlignmentY(0.0F);
        Bouton_Scores.setBorder(null);
        Bouton_Scores.setContentAreaFilled(false);
        Bouton_Scores.setMargin(new Insets(0, 0, 0, 0));
        Bouton_Scores.addActionListener(this::Bouton_ScoresActionPerformed);
        panel.add(Bouton_Scores, new AbsoluteConstraints(410, 300, -1, -1));

        Bouton_Options = new JButton();
        Bouton_Options.setIcon(new ImageIcon(getClass().getResource("/images/Bouton options_dark.png"))); // NOI18N
        Bouton_Options.setBorder(null);
        Bouton_Options.setContentAreaFilled(false);
        Bouton_Options.setMargin(new Insets(0, 0, 0, 0));
        Bouton_Options.addActionListener(this::Bouton_OptionsActionPerformed);
        panel.add(Bouton_Options, new AbsoluteConstraints(610, 300, -1, -1));

        Bouton_About = new JButton();
        Bouton_About.setIcon(new ImageIcon(getClass().getResource("/images/Bouton about_dark.png"))); // NOI18N
        Bouton_About.setBorder(null);
        Bouton_About.setContentAreaFilled(false);
        Bouton_About.setMargin(new Insets(0, 0, 0, 0));
        Bouton_About.addActionListener(this::Bouton_AboutActionPerformed);
        panel.add(Bouton_About, new AbsoluteConstraints(830, 530, -1, -1));

        this.getContentPane().add(panel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }                      
                          
    private void Bouton_AboutActionPerformed(ActionEvent evt) {                                          
            new RegleGUI();
    } 
    
    private void Bouton_JouerActionPerformed(ActionEvent evt) {                                          
            new ModeGUI();
    } 
    
    private void Bouton_OptionsActionPerformed(ActionEvent evt) {                                          
            //TODO
    } 
    
    private void Bouton_ScoresActionPerformed(ActionEvent evt) {                                          
        try {
            new ScoreGUI();
        } catch (IOException ex) {
            Logger.getLogger(MenuGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }     
}
