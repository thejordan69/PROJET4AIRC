package view;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import static javax.swing.SwingConstants.CENTER;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

public class MenuGUI extends JFrame {
    private JButton Bouton_About, Bouton_Jouer, Bouton_Options, Bouton_Scores;
    private JLabel jLabel1;     
    
    public MenuGUI() {
        initComponents();
    }
                          
    private void initComponents() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Menu");
        this.setResizable(false);
        this.getContentPane().setLayout(new AbsoluteLayout());
        
        Bouton_Jouer = new JButton();
        Bouton_Scores = new JButton();
        Bouton_Options = new JButton();
        Bouton_About = new JButton();
        jLabel1 = new JLabel();

        Bouton_Jouer.setIcon(new ImageIcon(getClass().getResource("/images/Bouton jouer_dark.png")));
        Bouton_Jouer.setBorder(null);
        Bouton_Jouer.setContentAreaFilled(false);
        Bouton_Jouer.setMargin(new Insets(0, 0, 0, 0));
        Bouton_Jouer.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Bouton_JouerMouseClicked(evt);
            }
        });
        this.getContentPane().add(Bouton_Jouer, new AbsoluteConstraints(210, 300, -1, -1));

        Bouton_Scores.setIcon(new ImageIcon(getClass().getResource("/images/Bouton scores_dark.png"))); // NOI18N
        Bouton_Scores.setAlignmentY(0.0F);
        Bouton_Scores.setBorder(null);
        Bouton_Scores.setContentAreaFilled(false);
        Bouton_Scores.setMargin(new java.awt.Insets(0, 0, 0, 0));
        Bouton_Scores.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Bouton_ScoresMouseClicked(evt);
            }
        });
        this.getContentPane().add(Bouton_Scores, new AbsoluteConstraints(410, 300, -1, -1));

        Bouton_Options.setIcon(new ImageIcon(getClass().getResource("/images/Bouton options_dark.png"))); // NOI18N
        Bouton_Options.setBorder(null);
        Bouton_Options.setContentAreaFilled(false);
        Bouton_Options.setMargin(new Insets(0, 0, 0, 0));
        Bouton_Options.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Bouton_OptionsMouseClicked(evt);
            }
        });
        this.getContentPane().add(Bouton_Options, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 300, -1, -1));

        Bouton_About.setIcon(new ImageIcon(getClass().getResource("/images/Bouton about_dark.png"))); // NOI18N
        Bouton_About.setBorder(null);
        Bouton_About.setContentAreaFilled(false);
        Bouton_About.setMargin(new java.awt.Insets(0, 0, 0, 0));
        Bouton_About.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Bouton_AboutMouseClicked(evt);
            }
        });   
        this.getContentPane().add(Bouton_About, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 530, -1, -1));

        jLabel1.setHorizontalAlignment(CENTER);
        jLabel1.setIcon(new ImageIcon(getClass().getResource("/images/Fond_rouge_50_Titre.jpg"))); // NOI18N
        jLabel1.setAlignmentY(0.0F);
        jLabel1.setMaximumSize(new Dimension(960, 590));
        jLabel1.setMinimumSize(new Dimension(960, 590));
        jLabel1.setPreferredSize(new Dimension(960, 590));
        this.getContentPane().add(jLabel1, new AbsoluteConstraints(0, 0, 960, 590));
        
       this.pack();
       this.setLocationRelativeTo(null);
    }                      
                          
    private void Bouton_AboutMouseClicked(MouseEvent evt) {                                          
            new RegleGUI().setVisible(true);
    } 
    
    private void Bouton_JouerMouseClicked(MouseEvent evt) {                                          
            new PseudoGUI().setVisible(true);
    } 
    
    private void Bouton_OptionsMouseClicked(MouseEvent evt) {                                          
            //TODO
    } 
    
    private void Bouton_ScoresMouseClicked(MouseEvent evt) {                                          
            //TODO
    }     
}
