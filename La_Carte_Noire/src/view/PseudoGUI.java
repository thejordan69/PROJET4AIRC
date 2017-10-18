package view;

import controler.GameControler;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import model.Joueur;
import model.Plateau;

public class PseudoGUI extends JFrame {
    private JTextField TF_Joueur1, TF_Joueur2, TF_Joueur3, TF_Joueur4;
    private JCheckBox CB_Equipe;
    private JButton BT_Retour, BT_Jouer;
    private JLabel jLabel1, jLabel2, jLabel3, jLabel4, jLabel5;
    private JSeparator jSeparator3;
    private PlateauGUI plateauGUI;
    private GameControler controler;
    private Plateau plateau;
    private Dimension dim;
    
    public PseudoGUI() {
        initComponents();
    }
 
    public void initComponents() {
        this.setTitle("Sélection des pseudos");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
        //création des différents éléments de la JFrame
        TF_Joueur1 = new JTextField();
        BT_Retour = new JButton();
        jLabel5 = new JLabel();
        jLabel1 = new JLabel();
        TF_Joueur2 = new JTextField();
        jLabel2 = new JLabel();
        TF_Joueur3 = new JTextField();
        jLabel3 = new JLabel();
        jSeparator3 = new JSeparator();
        jLabel4 = new JLabel();
        TF_Joueur4 = new JTextField();
        CB_Equipe = new JCheckBox();
        BT_Jouer = new JButton();
        
        //initialisation des valeurs textes des JLabels
        BT_Retour.setText("<- Retour");
        jLabel1.setText("Joueur 1 :");
        jLabel2.setText("Joueur 2 :");
        jLabel3.setText("Joueur 3 :");
        jLabel4.setText("Joueur 4 :");
        jLabel5.setText("Selection des joueurs : ");
        CB_Equipe.setText("Equipe");
        BT_Jouer.setText("Jouer");
        BT_Jouer.setEnabled(false);
        CB_Equipe.setEnabled(false);
        
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

        //code généré automatique par l'IDE pour la création de la JFrame
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(32, 32, 32)
                    .addComponent(BT_Retour)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 91, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(CB_Equipe)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addGap(18, 18, 18)
                                    .addComponent(TF_Joueur3, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addGap(18, 18, 18)
                                    .addComponent(TF_Joueur4, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addGap(18, 18, 18)
                                    .addComponent(TF_Joueur1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(BT_Jouer)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addGap(18, 18, 18)
                                    .addComponent(TF_Joueur2, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING))))
                    .addGap(32, 32, 32)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(8, 8, 8)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(BT_Retour)
                        .addComponent(jLabel5))
                    .addGap(18, 18, 18)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(TF_Joueur1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1))
                    .addGap(29, 29, 29)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(TF_Joueur2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2))
                    .addGap(28, 28, 28)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(TF_Joueur3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(TF_Joueur4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4))
                    .addGap(18, 18, 18)
                    .addComponent(CB_Equipe)
                    .addGap(15, 15, 15)
                    .addComponent(BT_Jouer)
                    .addGap(8, 8, 8)))
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
        pseudo.add(TF_Joueur1.getText());
        pseudo.add(TF_Joueur2.getText());
        pseudo.add(TF_Joueur3.getText());
        pseudo.add(TF_Joueur4.getText());

        for(String tmp : pseudo){
           if(!tmp.equals("") && Collections.frequency(pseudo,tmp) == 1){
               BT_Jouer.setEnabled(true);
           }
           else{
               BT_Jouer.setEnabled(false);
           }
        }
        }

    

    private void creerPartie(){
        ArrayList<Joueur> listeJoueurs;
        
        /*listeJoueurs.add(new Joueur(TF_Joueur1.getText()));
        listeJoueurs.add(new Joueur(TF_Joueur2.getText()));
        if(!TF_Joueur3.getText().equals("")){
            listeJoueurs.add(new Joueur(TF_Joueur3.getText()));
        }
        if(!TF_Joueur4.getText().equals("")){
            listeJoueurs.add(new Joueur(TF_Joueur4.getText()));
        }
        plateau = new Plateau(listeJoueurs);
        controler = new GameControler(plateau);
        dim = new Dimension(800,800);
        plateauGUI = new PlateauGUI(controler, dim);
        controler.addObserver((Observer) plateauGUI);*/
    }
}

