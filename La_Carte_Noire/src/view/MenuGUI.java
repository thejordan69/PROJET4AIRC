
package view;

/**
 *
 * @author FREY
 */
public class MenuGUI extends javax.swing.JFrame {

    /**
     * Creates new form MainMenu
     */
    public MenuGUI() {
        initComponents();
    }


    @SuppressWarnings("unchecked")
                          
    private void initComponents() {

        Bouton_Jouer = new javax.swing.JButton();
        Bouton_Scores = new javax.swing.JButton();
        Bouton_Options = new javax.swing.JButton();
        Bouton_About = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Bouton_Jouer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Bouton jouer_dark.png"))); // NOI18N
        Bouton_Jouer.setMnemonic('j');
        Bouton_Jouer.setToolTipText("");
        Bouton_Jouer.setBorder(null);
        Bouton_Jouer.setBorderPainted(false);
        Bouton_Jouer.setContentAreaFilled(false);
        Bouton_Jouer.setMargin(new java.awt.Insets(0, 0, 0, 0));
        Bouton_Jouer.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Bouton_JouerMouseClicked(evt);
            }
        });
        getContentPane().add(Bouton_Jouer, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 300, -1, -1));

        Bouton_Scores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Bouton scores_dark.png"))); // NOI18N
        Bouton_Scores.setMnemonic('s');
        Bouton_Scores.setToolTipText("");
        Bouton_Scores.setAlignmentY(0.0F);
        Bouton_Scores.setBorder(null);
        Bouton_Scores.setBorderPainted(false);
        Bouton_Scores.setContentAreaFilled(false);
        Bouton_Scores.setMargin(new java.awt.Insets(0, 0, 0, 0));
        Bouton_Scores.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Bouton_ScoresMouseClicked(evt);
            }
        });
        getContentPane().add(Bouton_Scores, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 300, -1, -1));

        Bouton_Options.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Bouton options_dark.png"))); // NOI18N
        Bouton_Options.setMnemonic('o');
        Bouton_Options.setToolTipText("");
        Bouton_Options.setBorder(null);
        Bouton_Options.setBorderPainted(false);
        Bouton_Options.setContentAreaFilled(false);
        Bouton_Options.setMargin(new java.awt.Insets(0, 0, 0, 0));
        Bouton_Options.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Bouton_OptionsMouseClicked(evt);
            }
        });
        getContentPane().add(Bouton_Options, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 300, -1, -1));

        Bouton_About.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Bouton about_dark.png"))); // NOI18N
        Bouton_About.setMnemonic('a');
        Bouton_About.setToolTipText("");
        Bouton_About.setBorder(null);
        Bouton_About.setBorderPainted(false);
        Bouton_About.setContentAreaFilled(false);
        Bouton_About.setMargin(new java.awt.Insets(0, 0, 0, 0));
        Bouton_About.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Bouton_AboutMouseClicked(evt);
            }
        });
        
        getContentPane().add(Bouton_About, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 530, -1, -1));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Fond_rouge_50_Titre.jpg"))); // NOI18N
        jLabel1.setAlignmentY(0.0F);
        jLabel1.setMaximumSize(new java.awt.Dimension(960, 590));
        jLabel1.setMinimumSize(new java.awt.Dimension(960, 590));
        jLabel1.setPreferredSize(new java.awt.Dimension(960, 590));
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 590));

        pack();
    }                      

                                       

    private void Bouton_AboutMouseClicked(java.awt.event.MouseEvent evt) {                                          

        java.awt.EventQueue.invokeLater(() -> {
            new RegleGUI().setVisible(true);
        });
    } 
    
    private void Bouton_JouerMouseClicked(java.awt.event.MouseEvent evt) {                                          

        java.awt.EventQueue.invokeLater(() -> {
            new PseudoGUItest().setVisible(true);
        });
    } 
    
    private void Bouton_OptionsMouseClicked(java.awt.event.MouseEvent evt) {                                          

        java.awt.EventQueue.invokeLater(() -> {
            //TODO
        });
    } 
    
    private void Bouton_ScoresMouseClicked(java.awt.event.MouseEvent evt) {                                          

        java.awt.EventQueue.invokeLater(() -> {
            //TODO
        });
    } 
                                          

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new MenuGUI().setVisible(true);
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton Bouton_About;
    private javax.swing.JButton Bouton_Jouer;
    private javax.swing.JButton Bouton_Options;
    private javax.swing.JButton Bouton_Scores;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration                   
}






/*
package view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class MenuGUI extends javax.swing.JFrame {
    private PlateauGUI plateauGUI;
    private PseudoGUI pseudoGUI;
    private RegleGUI regleGUI;
    private final JButton Bouton_Jouer;
    private final JButton Bouton_Scores;
    private final JButton Bouton_Options;
    private final JButton Bouton_About;
    private final JLabel jLabel1;
    
    public MenuGUI(){
        //création de la JFrame
        this.setTitle("Menu");
        this.setSize(400, 500);
	this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);             
        
        //création des boutons
        Bouton_Jouer = new javax.swing.JButton();
        Bouton_Scores = new javax.swing.JButton();
        Bouton_Options = new javax.swing.JButton();
        Bouton_About = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        
        //Design
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        
        //  JLabel
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/La_Carte_Noire/images/Fond_rouge_50_Titre.jpg"))); // NOI18N
        jLabel1.setAlignmentY(0.0F);
        jLabel1.setMaximumSize(new java.awt.Dimension(960, 590));
        jLabel1.setMinimumSize(new java.awt.Dimension(960, 590));
        jLabel1.setPreferredSize(new java.awt.Dimension(960, 590));
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 590));

        pack();
        
        
        
        
        //ajout des listeners aux boutons 
        Bouton_Jouer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
               SwingUtilities.getWindowAncestor(getContentPane()).setVisible(false);
               pseudoGUI = new PseudoGUI();
               pseudoGUI.setVisible(true);
          /*    HashMap<String, Integer> mapJoueurs = new HashMap();
                mapJoueurs.put("Damien",1);
                mapJoueurs.put("Marion",2);
                mapJoueurs.put("Jordan",2);
                mapJoueurs.put("Fred",1);
                Dimension dimPlateau = new Dimension(800,800);
                Dimension dimRecap = new Dimension(400,800);
                plateauGUI = new PlateauGUI(dimPlateau,dimRecap,mapJoueurs);
            }
        }); 
        Bouton_About.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                SwingUtilities.getWindowAncestor(getContentPane()).setVisible(false);
                regleGUI = new RegleGUI();
                regleGUI.setVisible(true);
            }
        }); 
        //ajout des boutons à la JFrame
        this.setLayout(new GridLayout(4, 1));
        this.add(Bouton_Jouer);
        this.add(Bouton_About);
        this.add(Bouton_Options);
        this.add(Bouton_Scores);  
        this.setVisible(true);
    }	   
}*/
