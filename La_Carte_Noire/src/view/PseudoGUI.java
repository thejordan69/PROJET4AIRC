package view;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

public class PseudoGUI extends JFrame {
    private JTextField TF_Joueur1, TF_Joueur2, TF_Joueur3, TF_Joueur4 ;
    private JRadioButton RB_Equipe1_Joueur1, RB_Equipe1_Joueur2, RB_Equipe1_Joueur3, RB_Equipe1_Joueur4,RB_Equipe2_Joueur1, RB_Equipe2_Joueur2, RB_Equipe2_Joueur3, RB_Equipe2_Joueur4;
    private JButton BT_Retour, BT_Jouer;
    private JCheckBox CB_Equipe;
    private JLabel JL_Joueur1, JL_Joueur2, JL_Joueur3, JL_Joueur4, JL_Select_Equipe, JL_Equipe2, JL_Equipe1, JL_Fond, JL_Erreur;
    private PlateauGUI plateauGUI;
    private Dimension dimPlateau, dimRecap;
    private ButtonGroup GP_Joueur1 , GP_Joueur2 , GP_Joueur3 , GP_Joueur4;
    
    public PseudoGUI() {
        initComponents();
    }
 
    public void initComponents() {
        this.setTitle("Sélection des pseudos");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(new AbsoluteLayout());
	
        //création des différents éléments de la JFrame
        JL_Fond = new JLabel();
        JL_Equipe1 = new JLabel();
        JL_Equipe1.setText("Equipe 1");
        JL_Equipe2 = new JLabel();
        JL_Equipe2.setText("Equipe 2");
        JL_Select_Equipe = new JLabel();
        JL_Select_Equipe.setText("Selection des joueurs : ");
        TF_Joueur1 = new JTextField();
        JL_Joueur1 = new JLabel();
        JL_Joueur1.setIcon(new ImageIcon(getClass().getResource("/images/joueur_1.png")));
        TF_Joueur2 = new JTextField();
        JL_Joueur2 = new JLabel();
        JL_Joueur2.setIcon(new ImageIcon(getClass().getResource("/images/joueur_2.png")));
        TF_Joueur3 = new JTextField();
        JL_Joueur3 = new JLabel();
        JL_Joueur3.setIcon(new ImageIcon(getClass().getResource("/images/joueur_3.png")));
        TF_Joueur4 = new JTextField();
        JL_Joueur4 = new JLabel();
        JL_Joueur4.setIcon(new ImageIcon(getClass().getResource("/images/joueur_4.png")));
        CB_Equipe = new JCheckBox("Equipe");
        CB_Equipe.setContentAreaFilled(false);
        JL_Erreur = new JLabel("Le nombre de joueurs par équipe est incorrect");
        JL_Erreur.setVisible(false);
        RB_Equipe1_Joueur1 = new JRadioButton();
        RB_Equipe1_Joueur1.setContentAreaFilled(false);
        RB_Equipe1_Joueur1.setActionCommand("Equipe1");
        RB_Equipe1_Joueur2 = new JRadioButton();
        RB_Equipe1_Joueur2.setContentAreaFilled(false);
        RB_Equipe1_Joueur2.setActionCommand("Equipe1");
        RB_Equipe1_Joueur3 = new JRadioButton();
        RB_Equipe1_Joueur3.setContentAreaFilled(false);
        RB_Equipe1_Joueur3.setActionCommand("Equipe1");
        RB_Equipe1_Joueur4 = new JRadioButton();
        RB_Equipe1_Joueur4.setContentAreaFilled(false);
        RB_Equipe1_Joueur4.setActionCommand("Equipe1");
        RB_Equipe2_Joueur1 = new JRadioButton();
        RB_Equipe2_Joueur1.setContentAreaFilled(false);
        RB_Equipe2_Joueur1.setActionCommand("Equipe2");
        RB_Equipe2_Joueur2 = new JRadioButton();
        RB_Equipe2_Joueur2.setContentAreaFilled(false);
        RB_Equipe2_Joueur2.setActionCommand("Equipe2");
        RB_Equipe2_Joueur3 = new JRadioButton();
        RB_Equipe2_Joueur3.setContentAreaFilled(false);
        RB_Equipe2_Joueur3.setActionCommand("Equipe2");
        RB_Equipe2_Joueur4 = new JRadioButton();
        RB_Equipe2_Joueur4.setContentAreaFilled(false);
        RB_Equipe2_Joueur4.setActionCommand("Equipe2");
        GP_Joueur1 = new ButtonGroup();
        GP_Joueur2 = new ButtonGroup();
        GP_Joueur3 = new ButtonGroup();
        GP_Joueur4 = new ButtonGroup();
        GP_Joueur1.add(RB_Equipe1_Joueur1);
        GP_Joueur1.add(RB_Equipe2_Joueur1);
        GP_Joueur2.add(RB_Equipe1_Joueur2);
        GP_Joueur2.add(RB_Equipe2_Joueur2);
        GP_Joueur3.add(RB_Equipe1_Joueur3);
        GP_Joueur3.add(RB_Equipe2_Joueur3);      
        GP_Joueur4.add(RB_Equipe1_Joueur4);
        GP_Joueur4.add(RB_Equipe2_Joueur4);  
        BT_Retour = new JButton();
        BT_Jouer = new JButton();
   
        BT_Jouer.setIcon(new ImageIcon(getClass().getResource("/images/Bouton jouer_dark.png"))); // NOI18N
        BT_Jouer.setBorder(null);
        BT_Jouer.setContentAreaFilled(false);
        BT_Jouer.setMargin(new Insets(0, 0, 0, 0));
        
        JL_Fond.setHorizontalAlignment(SwingConstants.CENTER);
        JL_Fond.setIcon(new ImageIcon(getClass().getResource("/images/Fond_rouge_50_Titre.jpg")));
        JL_Fond.setAlignmentY(0.0F);
        JL_Fond.setPreferredSize(new Dimension(960, 590));
        
        //Ajout des composants à la JFrame
        this.getContentPane().add(JL_Equipe1, new AbsoluteConstraints(100, 150, -1, -1));
        this.getContentPane().add(JL_Equipe2, new AbsoluteConstraints(230, 150, -1, -1));
        this.getContentPane().add(RB_Equipe1_Joueur1, new AbsoluteConstraints(100, 200, -1, -1));
        this.getContentPane().add(RB_Equipe2_Joueur1, new AbsoluteConstraints(230, 200, -1, -1));
        this.getContentPane().add(RB_Equipe1_Joueur2, new AbsoluteConstraints(100, 275, -1, -1));
        this.getContentPane().add(RB_Equipe2_Joueur2, new AbsoluteConstraints(230, 275, -1, -1));
        this.getContentPane().add(RB_Equipe1_Joueur3, new AbsoluteConstraints(100, 350, -1, -1));
        this.getContentPane().add(RB_Equipe2_Joueur3, new AbsoluteConstraints(230, 350, -1, -1));
        this.getContentPane().add(RB_Equipe1_Joueur4, new AbsoluteConstraints(100, 420, -1, -1));
        this.getContentPane().add(RB_Equipe2_Joueur4, new AbsoluteConstraints(230, 420, -1, -1));
        this.getContentPane().add(JL_Joueur1, new AbsoluteConstraints(390, 200, -1, -1));
        this.getContentPane().add(JL_Joueur2, new AbsoluteConstraints(390, 275, -1, -1));
        this.getContentPane().add(JL_Joueur3, new AbsoluteConstraints(390, 350, -1, -1));
        this.getContentPane().add(JL_Joueur4, new AbsoluteConstraints(390, 425, -1, -1));
        this.getContentPane().add(TF_Joueur1, new AbsoluteConstraints(650, 210, 160, 30));
        this.getContentPane().add(TF_Joueur2, new AbsoluteConstraints(650, 285, 160, 30));
        this.getContentPane().add(TF_Joueur4, new AbsoluteConstraints(650, 435, 160, 30));
        this.getContentPane().add(TF_Joueur3, new AbsoluteConstraints(650, 360, 160, 30));
        this.getContentPane().add(CB_Equipe, new AbsoluteConstraints(300, 480, -1, -1));
        this.getContentPane().add(JL_Erreur, new AbsoluteConstraints(80, 520, -1, -1));
        this.getContentPane().add(BT_Jouer, new AbsoluteConstraints(460, 510, -1, -1));
        this.getContentPane().add(JL_Fond, new AbsoluteConstraints(0, 0, 960, 590));

        //grisage des composants à désactiver
        JL_Equipe1.setEnabled(false);
        JL_Equipe2.setEnabled(false);
        BT_Jouer.setEnabled(false);
        CB_Equipe.setEnabled(false);
        RB_Equipe1_Joueur1.setEnabled(false);
        RB_Equipe1_Joueur2.setEnabled(false);
        RB_Equipe1_Joueur3.setEnabled(false);
        RB_Equipe1_Joueur4.setEnabled(false);
        RB_Equipe2_Joueur1.setEnabled(false);
        RB_Equipe2_Joueur2.setEnabled(false);
        RB_Equipe2_Joueur3.setEnabled(false);
        RB_Equipe2_Joueur4.setEnabled(false);
        
        //ajout des listeners aux différents composants
        TF_Joueur1.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                checkChamps();
            }
            public void removeUpdate(DocumentEvent e) {
                checkChamps();
            }
            public void changedUpdate(DocumentEvent e) {}
        });
        TF_Joueur2.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                checkChamps();
            }
            public void removeUpdate(DocumentEvent e) {
                checkChamps();
            }
            public void changedUpdate(DocumentEvent e) {}
        });
        TF_Joueur3.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                checkChamps();
            }
            public void removeUpdate(DocumentEvent e) {
                checkChamps();
            }
            public void changedUpdate(DocumentEvent e) {}
        });
        TF_Joueur4.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                checkChamps();
            }
            public void removeUpdate(DocumentEvent e) {
                checkChamps();
            }
            public void changedUpdate(DocumentEvent e) {}
        });
        BT_Retour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_RetourActionPerformed(evt);
            }
        });
        BT_Jouer.addActionListener(this::BT_JouerActionPerformed);  
        
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void BT_RetourActionPerformed(ActionEvent evt) {
        this.dispose();
        MenuGUI menu = new MenuGUI(); 
    }

    private void BT_JouerActionPerformed(ActionEvent evt) {
        creerPartie();
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
                 BT_Jouer.setEnabled(true);
                 CB_Equipe.setEnabled(true);
                 RB_Equipe1_Joueur1.setEnabled(true);
                 RB_Equipe1_Joueur2.setEnabled(true);
                 RB_Equipe1_Joueur3.setEnabled(true);
                 RB_Equipe1_Joueur4.setEnabled(true);
                 RB_Equipe2_Joueur1.setEnabled(true);
                 RB_Equipe2_Joueur2.setEnabled(true);
                 RB_Equipe2_Joueur3.setEnabled(true);
                 RB_Equipe2_Joueur4.setEnabled(true);
                 JL_Equipe2.setEnabled(true);
                 JL_Equipe1.setEnabled(true);
            }
            else {
                 BT_Jouer.setEnabled(true);
                 CB_Equipe.setEnabled(false);
                 RB_Equipe1_Joueur1.setEnabled(false);
                 RB_Equipe1_Joueur2.setEnabled(false);
                 RB_Equipe1_Joueur3.setEnabled(false);
                 RB_Equipe1_Joueur4.setEnabled(false);
                 RB_Equipe2_Joueur1.setEnabled(false);
                 RB_Equipe2_Joueur2.setEnabled(false);
                 RB_Equipe2_Joueur3.setEnabled(false);
                 RB_Equipe2_Joueur4.setEnabled(false);
                 JL_Equipe2.setEnabled(false);
                 JL_Equipe1.setEnabled(false);
            }
                }        
        else {
            BT_Jouer.setEnabled(false);
            CB_Equipe.setEnabled(false);
            RB_Equipe1_Joueur1.setEnabled(false);
            RB_Equipe1_Joueur2.setEnabled(false);
            RB_Equipe1_Joueur3.setEnabled(false);
            RB_Equipe1_Joueur4.setEnabled(false);
            RB_Equipe2_Joueur1.setEnabled(false);
            RB_Equipe2_Joueur2.setEnabled(false);
            RB_Equipe2_Joueur3.setEnabled(false);
            RB_Equipe2_Joueur4.setEnabled(false);
            JL_Equipe2.setEnabled(false);
            JL_Equipe1.setEnabled(false);
        }
        }

    private int checkNbJoueur(ArrayList<String> pseudo){
        int count=0;
        
        for(String tmp : pseudo){
            if(!tmp.equals("")){
                count = count + 1;
            }
        }
            
        return count;
    }
    
    private boolean checkJoueursEquipe(){
        int compteurE1 = 0, compteurE2 =0;
        ArrayList<ButtonGroup> groupesBoutons = new ArrayList<ButtonGroup>();
        groupesBoutons.add(GP_Joueur1);
        groupesBoutons.add(GP_Joueur2);
        groupesBoutons.add(GP_Joueur3);
        groupesBoutons.add(GP_Joueur4);
        
        for(ButtonGroup tmp : groupesBoutons){
            if(tmp.getSelection() != null){
                if(tmp.getSelection().getActionCommand().equals("Equipe1")){
                    compteurE1++;
                }else{
                    compteurE2++;
                }
            } 
        }
        return ((compteurE1 == 2) && (compteurE2 == 2));
    }
    
    private void creerPartie(){
        String equipe;
        int compteur;
        ArrayList<ButtonGroup> groupesBoutons;
        HashMap<String, Integer> mapJoueurs = new HashMap<String,Integer>();
        ArrayList<String> pseudo = new ArrayList<String>();
        pseudo.add(TF_Joueur1.getText());
        pseudo.add(TF_Joueur2.getText());
        pseudo.add(TF_Joueur3.getText());
        pseudo.add(TF_Joueur4.getText());
        
        //si 2 ou 3 joueurs => mode chacun pour soit
        if (checkNbJoueur(pseudo) == 2){
            mapJoueurs.put(TF_Joueur1.getText(),0);
            mapJoueurs.put(TF_Joueur2.getText(),0);
        }  
        else if (checkNbJoueur(pseudo) == 3){
            mapJoueurs.put(TF_Joueur1.getText(),0);
            mapJoueurs.put(TF_Joueur2.getText(),0);
            mapJoueurs.put(TF_Joueur3.getText(),0);
        }
        //si 4 joueurs => mode par équipe ou chacun pour soit
        else if (checkNbJoueur(pseudo) == 4){
            //jeu par équipe
            if(CB_Equipe.isSelected()){
                if(checkJoueursEquipe()){
                    groupesBoutons = new ArrayList<ButtonGroup>();
                    groupesBoutons.add(GP_Joueur1);
                    groupesBoutons.add(GP_Joueur2);
                    groupesBoutons.add(GP_Joueur3);
                    groupesBoutons.add(GP_Joueur4);
                    
                    compteur = 0;
                    for(ButtonGroup tmp : groupesBoutons){
                        if(tmp.getSelection().getActionCommand().equals("Equipe1")){
                            mapJoueurs.put(pseudo.get(compteur),1);
                        }else{
                            mapJoueurs.put(pseudo.get(compteur),2);
                        }
                        compteur++;              
                    }      
                }
                //il n'y a pas le bon nombre de joueurs dans chaque équipe
                else{
                    JL_Erreur.setVisible(true);
                    return;
                } 
            }
            //jeu chacun pour soit
            else{
                for(String tmp : pseudo){
                    mapJoueurs.put(tmp,0);
                }
            }
        }
        
        dimPlateau = new Dimension(800,800);
        dimRecap = new Dimension(400,800);
        plateauGUI = new PlateauGUI(dimPlateau,dimRecap,mapJoueurs); 
        this.dispose();
    }
}