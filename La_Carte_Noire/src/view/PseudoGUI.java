package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class PseudoGUI extends JFrame {
    private JTextField TF_Joueur1, TF_Joueur2, TF_Joueur3, TF_Joueur4 ;
    private JRadioButton CB_Equipe, CB_Equipe1_Joueur1, CB_Equipe1_Joueur2, CB_Equipe1_Joueur3, CB_Equipe1_Joueur4,CB_Equipe2_Joueur1, CB_Equipe2_Joueur2, CB_Equipe2_Joueur3, CB_Equipe2_Joueur4;
    private JButton BT_Retour, BT_Jouer;
    private JLabel JL_Joueur1, JL_Joueur2, JL_Joueur3, JL_Joueur4, JL_Select_Equipe, JL_Equipe2, JL_Equipe1;
    private JSeparator jSeparator3;
    private PlateauGUI plateauGUI;
    private Dimension dimPlateau, dimRecap;
    private ButtonGroup GP_Joueur1 , GP_Joueur2 , GP_Joueur3 , GP_Joueur4;
    
    public PseudoGUI() {
        initComponents();
    }
 
    public void initComponents() {
        this.setTitle("Sélection des pseudos");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
        //création des différents éléments de la JFrame
        TF_Joueur1 = new JTextField();
        JL_Equipe1 = new JLabel();
        JL_Equipe2 = new JLabel();
        BT_Retour = new JButton();
        JL_Select_Equipe = new JLabel();
        JL_Joueur1 = new JLabel();
        TF_Joueur2 = new JTextField();
        JL_Joueur2 = new JLabel();
        TF_Joueur3 = new JTextField();
        JL_Joueur3 = new JLabel();
        jSeparator3 = new JSeparator();
        JL_Joueur4 = new JLabel();
        TF_Joueur4 = new JTextField();
        CB_Equipe = new JRadioButton();
        CB_Equipe1_Joueur1 = new JRadioButton();
        CB_Equipe1_Joueur2 = new JRadioButton();
        CB_Equipe1_Joueur3 = new JRadioButton();
        CB_Equipe1_Joueur4 = new JRadioButton();
        CB_Equipe2_Joueur1 = new JRadioButton();
        CB_Equipe2_Joueur2 = new JRadioButton();
        CB_Equipe2_Joueur3 = new JRadioButton();
        CB_Equipe2_Joueur4 = new JRadioButton();
        GP_Joueur1 = new ButtonGroup();
        GP_Joueur2 = new ButtonGroup();
        GP_Joueur3 = new ButtonGroup();
        GP_Joueur4 = new ButtonGroup();
        GP_Joueur1.add(CB_Equipe1_Joueur1);
        GP_Joueur1.add(CB_Equipe2_Joueur1);
        GP_Joueur2.add(CB_Equipe1_Joueur2);
        GP_Joueur2.add(CB_Equipe2_Joueur2);
        GP_Joueur3.add(CB_Equipe1_Joueur3);
        GP_Joueur3.add(CB_Equipe2_Joueur3);      
        GP_Joueur4.add(CB_Equipe1_Joueur4);
        GP_Joueur4.add(CB_Equipe2_Joueur4);
        
        
        BT_Jouer = new JButton();
        
        
        
        //initialisation des valeurs textes des JLabels
        BT_Retour.setText("<- Retour");
        JL_Joueur1.setText("Joueur 1 :");
        JL_Joueur2.setText("Joueur 2 :");
        JL_Joueur3.setText("Joueur 3 :");
        JL_Joueur4.setText("Joueur 4 :");
        JL_Select_Equipe.setText("Selection des joueurs : ");
        CB_Equipe.setText("Equipe");
        BT_Jouer.setText("Jouer");
        JL_Equipe1.setText("Equipe 1");
        JL_Equipe2.setText("Equipe 2");
        BT_Jouer.setEnabled(false);
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
        JL_Equipe1.setEnabled(false);
        
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
        
        CB_Equipe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CB_EquipeActionPerformed(evt);
            }
        });
  
        BT_Jouer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_JouerActionPerformed(evt);
            }
        });
        
        CB_Equipe1_Joueur1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CB_EquipeActionPerformed(evt);
            }
        });
        CB_Equipe2_Joueur1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CB_EquipeActionPerformed(evt);
            }
        });
        CB_Equipe1_Joueur2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CB_EquipeActionPerformed(evt);
            }
        });
        CB_Equipe2_Joueur2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CB_EquipeActionPerformed(evt);
            }
        });
        CB_Equipe1_Joueur3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CB_EquipeActionPerformed(evt);
            }
        });
         CB_Equipe2_Joueur3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CB_EquipeActionPerformed(evt);
            }
        });
          CB_Equipe1_Joueur4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CB_EquipeActionPerformed(evt);
            }
        });
          
          CB_Equipe2_Joueur4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CB_EquipeActionPerformed(evt);
            }
        });

        //code généré automatique par l'IDE pour la création de la JFrame
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CB_Equipe1_Joueur4)
                            .addComponent(CB_Equipe1_Joueur3)
                            .addComponent(CB_Equipe1_Joueur2)
                            .addComponent(CB_Equipe1_Joueur1)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(JL_Equipe1)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(JL_Equipe2)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(CB_Equipe2_Joueur2)
                                    .addComponent(CB_Equipe2_Joueur1)
                                    .addComponent(CB_Equipe2_Joueur3)
                                    .addComponent(CB_Equipe2_Joueur4))))))
                .addContainerGap(216, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(32, 32, 32)
                    .addComponent(BT_Retour)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 91, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(CB_Equipe)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(JL_Joueur3)
                                .addGap(18, 18, 18)
                                .addComponent(TF_Joueur3, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(JL_Joueur4)
                                .addGap(18, 18, 18)
                                .addComponent(TF_Joueur4, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(JL_Joueur1)
                                .addGap(18, 18, 18)
                                .addComponent(TF_Joueur1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(BT_Jouer)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(JL_Joueur2)
                                .addGap(18, 18, 18)
                                .addComponent(TF_Joueur2, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(JL_Select_Equipe, javax.swing.GroupLayout.Alignment.LEADING)))
                    .addGap(32, 32, 32)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(JL_Equipe1)
                                    .addComponent(JL_Equipe2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CB_Equipe2_Joueur1))
                            .addComponent(CB_Equipe1_Joueur1))
                        .addGap(26, 26, 26)
                        .addComponent(CB_Equipe2_Joueur2))
                    .addComponent(CB_Equipe1_Joueur2))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CB_Equipe2_Joueur3)
                    .addComponent(CB_Equipe1_Joueur3))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(CB_Equipe2_Joueur4)
                    .addComponent(CB_Equipe1_Joueur4))
                .addContainerGap(87, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(8, 8, 8)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(BT_Retour)
                        .addComponent(JL_Select_Equipe))
                    .addGap(18, 18, 18)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(TF_Joueur1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(JL_Joueur1))
                    .addGap(29, 29, 29)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(TF_Joueur2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(JL_Joueur2))
                    .addGap(28, 28, 28)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(TF_Joueur3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(JL_Joueur3))
                    .addGap(27, 27, 27)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(TF_Joueur4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(JL_Joueur4))
                    .addGap(18, 18, 18)
                    .addComponent(CB_Equipe)
                    .addGap(15, 15, 15)
                    .addComponent(BT_Jouer)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        
        this.pack();
        this.setLocationRelativeTo(null);
    }

    private void BT_RetourActionPerformed(ActionEvent evt) {
        this.dispose();
        MenuGUI menu = new MenuGUI(); 
    }

    private void CB_EquipeActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void BT_JouerActionPerformed(ActionEvent evt) {
        this.dispose();
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
                 CB_Equipe1_Joueur1.setEnabled(true);
                 CB_Equipe1_Joueur2.setEnabled(true);
                 CB_Equipe1_Joueur3.setEnabled(true);
                 CB_Equipe1_Joueur4.setEnabled(true);
                 CB_Equipe2_Joueur1.setEnabled(true);
                 CB_Equipe2_Joueur2.setEnabled(true);
                 CB_Equipe2_Joueur3.setEnabled(true);
                 CB_Equipe2_Joueur4.setEnabled(true);
                 JL_Equipe2.setEnabled(true);
                 JL_Equipe1.setEnabled(true);
            }
            else {
                 BT_Jouer.setEnabled(true);
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
                 JL_Equipe1.setEnabled(false);
            }
                }        
        else {
            BT_Jouer.setEnabled(false);
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
            JL_Equipe1.setEnabled(false);
        }
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
}