
package view;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


public class PseudoGUItest extends javax.swing.JFrame {


    public PseudoGUItest() {
        initComponents();
    }


    @SuppressWarnings("unchecked")
                           
    private void initComponents() {

        TF_Joueur1 = new javax.swing.JTextField();
        TF_Joueur2 = new javax.swing.JTextField();
        TF_Joueur3 = new javax.swing.JTextField();
        TF_Joueur4 = new javax.swing.JTextField();
        JL_Joueur1 = new javax.swing.JLabel();
        JL_Joueur2 = new javax.swing.JLabel();
        JL_Joueur3 = new javax.swing.JLabel();
        JL_Joueur4 = new javax.swing.JLabel();
        JL_Fond = new javax.swing.JLabel();
        BT_Jouer = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TF_Joueur1.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                checkChamps();
            }
            public void removeUpdate(DocumentEvent e) {
                checkChamps();
            }
            public void changedUpdate(DocumentEvent e) {}
        });
        getContentPane().add(TF_Joueur1, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 210, 160, 30));

        TF_Joueur2.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                checkChamps();
            }
            public void removeUpdate(DocumentEvent e) {
                checkChamps();
            }
            public void changedUpdate(DocumentEvent e) {}
        });
        getContentPane().add(TF_Joueur2, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 285, 160, 30));

        TF_Joueur3.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                checkChamps();
            }
            public void removeUpdate(DocumentEvent e) {
                checkChamps();
            }
            public void changedUpdate(DocumentEvent e) {}
        });
        getContentPane().add(TF_Joueur3, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 360, 160, 30));

        TF_Joueur4.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                checkChamps();
            }
            public void removeUpdate(DocumentEvent e) {
                checkChamps();
            }
            public void changedUpdate(DocumentEvent e) {}
        });
        getContentPane().add(TF_Joueur4, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 435, 160, 30));

        
        JL_Joueur1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/joueur_1.png")));
        getContentPane().add(JL_Joueur1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 200, -1, -1));
        
        JL_Joueur2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/joueur_2.png")));
        getContentPane().add(JL_Joueur2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 275, -1, -1));
        
        JL_Joueur3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/joueur_3.png")));
        getContentPane().add(JL_Joueur3, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 350, -1, -1));
        
        JL_Joueur4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/joueur_4.png")));
        getContentPane().add(JL_Joueur4, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 425, -1, -1));

        


        
        BT_Jouer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Bouton jouer_dark.png"))); // NOI18N
        BT_Jouer.setMnemonic('j');
        BT_Jouer.setToolTipText("");
        BT_Jouer.setVisible(true);
        BT_Jouer.setBorder(null);
        BT_Jouer.setBorderPainted(false);
        BT_Jouer.setContentAreaFilled(false);
        BT_Jouer.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BT_Jouer.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BT_JouerMouseClicked(evt);
            }
        });
        getContentPane().add(BT_Jouer, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 510, -1, -1));
        
        JL_Fond.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JL_Fond.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Fond_rouge_50_Titre.jpg"))); // NOI18N
        JL_Fond.setAlignmentY(0.0F);
        JL_Fond.setMaximumSize(new java.awt.Dimension(960, 590));
        JL_Fond.setMinimumSize(new java.awt.Dimension(960, 590));
        JL_Fond.setPreferredSize(new java.awt.Dimension(960, 590));
        getContentPane().add(JL_Fond, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 590));

        pack();
    }                  

                                      

    private int checkNbJoueur(ArrayList<String> pseudo){
        int i, count=0;
        
        for(String tmp : pseudo){
            if(!tmp.equals("")){
                count = count + 1;
            }
        }
            
        return count;
    }
 
    private void checkChamps(){
        ArrayList<String> pseudo = new ArrayList<String>();
        int i = 0;
        int count = 0;
        int nbJoueur = 0;
        pseudo.add(TF_Joueur1.getText());
        pseudo.add(TF_Joueur2.getText());
        pseudo.add(TF_Joueur3.getText());
        pseudo.add(TF_Joueur4.getText());
        
        nbJoueur = checkNbJoueur(pseudo);
        
        for(String tmp : pseudo){
           if(!tmp.equals("") && Collections.frequency(pseudo,tmp) == 1){
               count = count + 1;
           }
        }
        if ((count == nbJoueur) && (nbJoueur >1)){
            if (nbJoueur >3){
                 /*BT_Jouer.setEnabled(true);
                 CB_Equipe.setEnabled(true);
                 CB_Equipe1_Joueur1.setEnabled(true);
                 CB_Equipe1_Joueur2.setEnabled(true);
                 CB_Equipe1_Joueur3.setEnabled(true);
                 CB_Equipe1_Joueur4.setEnabled(true);
                 CB_Equipe2_Joueur1.setEnabled(true);
                 CB_Equipe2_Joueur2.setEnabled(true);
                 CB_Equipe2_Joueur3.setEnabled(true);
                 CB_Equipe2_Joueur4.setEnabled(true);
                 JL_Equipe2.setEnabled(true);
                 JL_Equipe1.setEnabled(true);*/
            }
            else {
                 /*BT_Jouer.setEnabled(true);
                 CB_Equipe.setEnabled(false);
                 CB_Equipe1_Joueur1.setEnabled(false);
                 CB_Equipe1_Joueur2.setEnabled(false);
                 CB_Equipe1_Joueur3.setEnabled(false);
                 CB_Equipe1_Joueur4.setEnabled(false);
                 CB_Equipe2_Joueur1.setEnabled(false);
                 CB_Equipe2_Joueur2.setEnabled(false);
                 CB_Equipe2_Joueur3.setEnabled(false);
                 CB_Equipe2_Joueur4.setEnabled(false);
                 JL_Equipe2.setEnabled(false);
                 JL_Equipe1.setEnabled(false);*/
            }
                }        
        else {
            /*BT_Jouer.setEnabled(false);
            CB_Equipe.setEnabled(false);
            CB_Equipe1_Joueur1.setEnabled(false);
            CB_Equipe1_Joueur2.setEnabled(false);
            CB_Equipe1_Joueur3.setEnabled(false);
            CB_Equipe1_Joueur4.setEnabled(false);
            CB_Equipe2_Joueur1.setEnabled(false);
            CB_Equipe2_Joueur2.setEnabled(false);
            CB_Equipe2_Joueur3.setEnabled(false);
            CB_Equipe2_Joueur4.setEnabled(false);
            JL_Equipe2.setEnabled(false);
            JL_Equipe1.setEnabled(false);*/
        }
        }
    
    private void BT_JouerMouseClicked(java.awt.event.MouseEvent evt) {                                          

        java.awt.EventQueue.invokeLater(() -> {
        this.dispose();
        creerPartie();
        });
    } 
    
        private void creerPartie(){
        HashMap<String, Integer> mapJoueurs = new HashMap();
        ArrayList<String> pseudo = new ArrayList<String>();
        pseudo.add(TF_Joueur1.getText());
        pseudo.add(TF_Joueur2.getText());
        pseudo.add(TF_Joueur3.getText());
        pseudo.add(TF_Joueur4.getText());
        
        if (checkNbJoueur(pseudo) == 2 )
        {
            mapJoueurs.put(TF_Joueur1.getText(),0);
            mapJoueurs.put(TF_Joueur2.getText(),0);
        }  
        else {if (checkNbJoueur(pseudo) == 3 ){
            mapJoueurs.put(TF_Joueur1.getText(),0);
            mapJoueurs.put(TF_Joueur2.getText(),0);
            mapJoueurs.put(TF_Joueur3.getText(),0);
        }else {if (checkNbJoueur(pseudo) == 4 ){
            mapJoueurs.put(TF_Joueur1.getText(),1);
            mapJoueurs.put(TF_Joueur2.getText(),2);
            mapJoueurs.put(TF_Joueur3.getText(),2);
            mapJoueurs.put(TF_Joueur4.getText(),1);
        }}}
            
       /* mapJoueurs.put("Damien",1);
        mapJoueurs.put("Marion",2);
        mapJoueurs.put("Jordan",2);
        mapJoueurs.put("Fred",1);*/
        dimPlateau = new Dimension(800,800);
        dimRecap = new Dimension(400,800);
        plateauGUI = new PlateauGUI(dimPlateau,dimRecap,mapJoueurs);
        
        mapJoueurs.put(TF_Joueur1.getText(),1);
        mapJoueurs.put(TF_Joueur2.getText(),2);
        
        if(checkNbJoueur(pseudo) == 3){
            mapJoueurs.put(TF_Joueur3.getText(),0);
        }
        if(checkNbJoueur(pseudo) == 4){
            mapJoueurs.put(TF_Joueur4.getText(),0);
        }
         
        dimPlateau = new Dimension(800,800);
        dimRecap = new Dimension(400,800);
        plateauGUI = new PlateauGUI(dimPlateau,dimRecap,mapJoueurs); 
    }
    
    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PseudoGUItest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PseudoGUItest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PseudoGUItest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PseudoGUItest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PseudoGUItest().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JLabel JL_Fond;
    private javax.swing.JLabel JL_Joueur1;
    private javax.swing.JLabel JL_Joueur2;
    private javax.swing.JLabel JL_Joueur3;
    private javax.swing.JLabel JL_Joueur4;
    private javax.swing.JTextField TF_Joueur1;
    private javax.swing.JTextField TF_Joueur3;
    private javax.swing.JTextField TF_Joueur4;
    private javax.swing.JTextField TF_Joueur2;
    private javax.swing.JButton BT_Jouer;
    private PlateauGUI plateauGUI;
    private Dimension dimPlateau, dimRecap;
    // End of variables declaration     
   
}

