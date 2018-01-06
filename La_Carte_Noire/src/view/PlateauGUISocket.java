package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import model.AbstractCarteIHM;
import model.Coord;
import model.Couleur;
import model.Jeton;
import model.JoueurIHM;
import controler.GameControlerSocket;
import tools.ImagePanel;
import tools.ImageProvider;

public class PlateauGUISocket extends AbstractPlateauGUI {
    private JFrame infoFrame;
    private String mode, pseudo, IP;
    private boolean isFirst = true;
    
    public PlateauGUISocket(Dimension plateauSize, Dimension recapSize, String pseudo, String IP, String mode) throws IOException {  
        super(plateauSize,recapSize);
        
        this.pseudo = pseudo;
        this.IP = IP;
        this.mode = mode;
        
        this.controler = new GameControlerSocket(pseudo,IP,mode);
        this.controler.addObserver((Observer) this); 
    }    

    @Override
    protected void initComponents(){
        layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(plateauSize);
        getContentPane().add(layeredPane,BorderLayout.WEST);
        layeredPane.addMouseListener(this);
        layeredPane.addMouseMotionListener(this);

        panel = new ImagePanel("plateau");
        panel.setLayout(new GridLayout(6,6));
        layeredPane.add(panel, JLayeredPane.DEFAULT_LAYER);
        panel.setPreferredSize(plateauSize);
        panel.setBounds(0,0,plateauSize.width,plateauSize.height);
        
        /*jlMessage = new JLabel("", JLabel.CENTER);
        jlMessage.setBorder(BorderFactory.createLineBorder(Color.red));
        jlMessage.setFont(jlMessage.getFont().deriveFont(15f));*/
        //this.getContentPane().add(jlMessage,BorderLayout.SOUTH);
        this.createGrid();
        this.createRecap(recapSize);
        
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    protected void createGrid(){
        super.createGrid();  
    }
    
    //méthode qui permet de créer le menu récapitulatif
    @Override
    protected void createRecap(Dimension recapSize){
        int i =0;
        
        recap = new ImagePanel("recap");
        recap.setPreferredSize(recapSize);
        getContentPane().add(recap,BorderLayout.EAST);

        //génération des titres des colonnes
        recapTitres = new JPanel(new GridLayout(0,4));
        recapTitres.setPreferredSize(new Dimension(recapSize.width,50));
        recapTitres.setOpaque(false);
        JLabel jlJeton = new JLabel("Jetons",JLabel.CENTER);
        jlJeton.setFont(jlJeton.getFont().deriveFont(20f));
        JLabel jlCarte = new JLabel("Cartes",JLabel.CENTER);
        jlCarte.setFont(jlJeton.getFont().deriveFont(20f));
        JLabel jlScore = new JLabel("Score",JLabel.CENTER);
        jlScore.setFont(jlJeton.getFont().deriveFont(20f));
        JLabel jlPseudo = new JLabel("Pseudo",JLabel.CENTER);
        jlPseudo.setFont(jlJeton.getFont().deriveFont(20f));
        recapTitres.add(jlJeton);
        recapTitres.add(jlCarte);
        recapTitres.add(jlScore);
        recapTitres.add(jlPseudo);
        recap.add(recapTitres);
        
        //affichage des scores des joueurs
        ArrayList<JoueurIHM> joueurs = controler.getJoueursIHM();
        recapJoueurs = new JPanel(new GridLayout(joueurs.size(),4));
        recapJoueurs.setOpaque(false);
        recapJoueurs.setPreferredSize(new Dimension(recapSize.width,recapSize.height-62));
        for(JoueurIHM joueur : joueurs){
            JPanel JPcurrent = new JPanel(new GridLayout(1,4));
            JPcurrent.setOpaque(false);
            JPanel JPpions = new JPanel(new GridLayout(6,1));
            JPpions.setOpaque(false);
            JPpions.setBorder(BorderFactory.createLineBorder(Color.black));
            JPcurrent.add(JPpions);
            JPanel JPcartes = new JPanel(new GridLayout(4,4));
            JPcartes.setOpaque(false);
            JPcartes.setBorder(BorderFactory.createLineBorder(Color.black));
            JPcurrent.add(JPcartes);      
            JLabel JLscore = new JLabel(joueur.getScore(),JLabel.CENTER);
            JLscore.setFont(JLscore.getFont().deriveFont(20f));
            JLscore.setBorder(BorderFactory.createLineBorder(Color.black));
            JLscore.setForeground(Color.BLACK);
            JPcurrent.add(JLscore);
            JLabel JLpseudo = new JLabel(joueur.getPseudo(),JLabel.CENTER);
            JLpseudo.setFont(JLpseudo.getFont().deriveFont(20f));
            JLpseudo.setBorder(BorderFactory.createLineBorder(Color.black));
            JLpseudo.setForeground(Color.BLACK);
            JPcurrent.add(JLpseudo);
            recapJoueurs.add(JPcurrent);
            i++;
        } 
        recap.add(recapJoueurs);
    }
                  
    @Override
    public void mouseReleased(MouseEvent e) {
        int movedIndex;
		
	if(carte == null) return;
        carte.setVisible(false);
        Component c =  layeredPane.findComponentAt(e.getX(), e.getY());
        //S'il y a une pièce à la destination
        if (c instanceof JLabel){
            JPanel newLocationPanel = (JPanel) c.getParent();
            movedIndex = (Integer) newLocationPanel.getClientProperty("index");
        //Si la case de destination est vide
        } else if (c instanceof JPanel){
            JPanel newLocationPanel = (JPanel) c;
            movedIndex = (Integer) newLocationPanel.getClientProperty("index");
        }
        //si la case n'est pas dans la plateau on regénère l'affichage du plateau et on stop
        else {
            update(null,null);
            //jlMessage.setText("Les coordonées sont hors limites");
            return;
        }

        //Calcul des coordonées source et destination à partir de l'index
        Coord initCoord = new Coord(oldIndex%6, oldIndex/6);
        //Calcul de l'index x,y destination
        Coord finalCoord = new Coord(movedIndex%6, movedIndex/6);
        boolean iSMoveSucceded = controler.move(initCoord, finalCoord);
        if(iSMoveSucceded){
            carte = null;
            isBlocked = true;
            ActionListener traitement = new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    controler.updateSocketState();
                }
            };
            Timer timer = new Timer(1000,traitement);
            timer.setRepeats(false);
            timer.start();
        }
    }

    @Override public void update(Observable o, Object arg) {
        int index, i = 0;
        JLabel jLabelCarte;
        JPanel pan;
        ArrayList<AbstractCarteIHM> listeCartes;
        ArrayList<Jeton> jetonsJoueur;
        ArrayList<JoueurIHM> joueurs;
        JoueurIHM joueurCourant;

        if(arg != null){
            if(((String) arg).equals("controler_cree")){
                return;
            }
            else if(mode.equals("Client") && ((String) arg).equals("no_socket")){
                createJDialogInfo();
                return;
            }
            else if(mode.equals("Serveur") && ((String) arg).equals("attente_client")){
                createJDialogInfo();
                return;
            }
            else if(mode.equals("Serveur") && ((String) arg).equals("client_connecté")){
                infoFrame.dispose();
            }
        }

        //permet d'initialiser la JFrame la première fois
        if(isFirst){
            isFirst = false;
            initComponents();
        }
        
        //permet de débloquer la JFrame lorsque le deuxième joueur a terminé son tour
        isBlocked = false;
        //récupère le message du plateau
        //jlMessage.setText(controler.getMessage());
        //Efface et recrée la grille
        panel.removeAll();
        this.createGrid();
        
        //regénération du nouveau plateau
        listeCartes = controler.getListeCartesIHM();
        for(AbstractCarteIHM tmp : listeCartes){
            //si la carte n'est pas déjà gagnée
            if((tmp.getX() != -1) && (tmp.getY() != -1)){
                index = tmp.getX()+tmp.getY()*6;
                jLabelCarte = new JLabel(new ImageIcon(ImageProvider.getImageFile("carte",tmp.getCouleur())));
                pan = (JPanel) panel.getComponent(index);
                pan.add(jLabelCarte);
            } 
        }
        
        //regénération des scores et des pions des joueurs
        joueurs = controler.getJoueursIHM();
        joueurCourant = controler.getJoueurCourantIHM();
        for(Component current : recapJoueurs.getComponents()){
            JPanel JPcurrent = (JPanel) current;
            JPcurrent.setOpaque(false);
            JoueurIHM joueur = joueurs.get(i);
            if(joueurCourant.getPseudo().equals(joueur.getPseudo())){
                JPcurrent.setBorder(BorderFactory.createLineBorder(Color.green,2));
            }
            else{
                JPcurrent.setBorder(BorderFactory.createEmptyBorder());
            }
            
            //affichage des cartes avec leur nombre associé
            HashMap<Couleur,Integer> mapCartes = joueur.getNombreCartes();
            HashMap<Couleur,Color> translate = new HashMap<Couleur,Color>();
            translate.put(Couleur.bleue,Color.BLUE);
            translate.put(Couleur.verte,Color.GREEN);
            translate.put(Couleur.rouge,Color.RED);
            translate.put(Couleur.rose,Color.PINK);
            translate.put(Couleur.cyan,Color.CYAN);
            translate.put(Couleur.orange,Color.ORANGE);
            translate.put(Couleur.jaune,Color.YELLOW);
            ((JPanel) JPcurrent.getComponent(1)).removeAll();
            for(Map.Entry<Couleur,Integer> tmp : mapCartes.entrySet()){
                JLabel nb = new JLabel(String.valueOf(tmp.getValue()),JLabel.CENTER);
                if(tmp.getKey() == Couleur.rose){
                    nb.setForeground(Color.MAGENTA);
                }
                else if(tmp.getKey() == Couleur.orange){
                    nb.setForeground(new Color(255,113,17));
                }
                else{
                    nb.setForeground(translate.get(tmp.getKey()));
                }
                nb.setFont(nb.getFont().deriveFont(20f));
                ((JPanel) JPcurrent.getComponent(1)).add(nb);
            }
          
            JLabel JLscore = (JLabel) JPcurrent.getComponent(2);
            JLscore.setText(joueur.getScore());

            jetonsJoueur = joueur.getListeJetons();
            JPanel JPpion = (JPanel) JPcurrent.getComponent(0);
            
            JPpion.removeAll();
            for(Jeton tmp : jetonsJoueur){    
                JLabel pion = new JLabel(new ImageIcon(ImageProvider.getImageFile("pion",tmp.getCouleur())),JLabel.CENTER);
                JPpion.add(pion);
            }
            i++;
        }
        this.repaint();
        
        //si c'est la fin de la partie, on arrête la partie, on sauvegarde les scores et on affiche le gagnant
        if(controler.isEnd()){
            //jlMessage.setText("La partie est terminée");
            controler.saveScores();
            JDialog winerFrame = new JDialog(this,"Fin de la partie",true);
            winerFrame.setSize(700,300);
            winerFrame.setUndecorated(true);
            winerFrame.setLocationRelativeTo(null);
            winerFrame.setLayout(new BorderLayout());
            JPanel panBackground = new ImagePanel("JDialog_Finale");
            panBackground.setLayout(new BorderLayout());
            panBackground.setPreferredSize(new Dimension(700,250));
            winerFrame.add(panBackground,BorderLayout.NORTH);
            JPanel panBoutons = new JPanel(new GridLayout(0,2));
            panBoutons.setPreferredSize(new Dimension(700,50));
            JButton bouton_rejouer = new JButton("Rejouer");
            JButton bouton_quitter = new JButton("Quitter");
            panBoutons.add(bouton_rejouer);
            panBoutons.add(bouton_quitter);
            winerFrame.add(panBoutons,BorderLayout.SOUTH);
            bouton_rejouer.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    SwingUtilities.getWindowAncestor(getContentPane()).dispose();
                    Dimension dimPlateau = new Dimension(800,800);
                    Dimension dimRecap = new Dimension(400,800);
                    try {
                        PlateauGUISocket newPlateau = new PlateauGUISocket(dimPlateau,dimRecap,pseudo,IP,mode);
                    } catch (IOException ex) {
                        Logger.getLogger(PlateauGUISocket.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }); 
            bouton_quitter.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    SwingUtilities.getWindowAncestor(getContentPane()).dispose();
                }
            }); 
            JLabel gagnant = new JLabel("",JLabel.CENTER);
            gagnant.setText("Le gagnant est :" + controler.getGagnant());
            gagnant.setFont(gagnant.getFont().deriveFont(34f));
            gagnant.setForeground(Color.BLACK);
            gagnant.setBorder(new CompoundBorder(gagnant.getBorder(), new EmptyBorder(0,0,20,0)));   
            panBackground.add(gagnant,BorderLayout.SOUTH);
            winerFrame.setVisible(true);
        }
    }
    
    private void createJDialogInfo(){
        if(mode.equals("Client")){
            infoFrame = new JFrame("Erreur");
            infoFrame.setSize(700,300);
            infoFrame.setUndecorated(true);
            infoFrame.setLocationRelativeTo(null);
            infoFrame.setLayout(new BorderLayout());
            JPanel panBackground = new ImagePanel("JDialog_Client");
            panBackground.setLayout(new BorderLayout());
            panBackground.setPreferredSize(new Dimension(700,250));
            infoFrame.add(panBackground,BorderLayout.NORTH);
            JLabel info = new JLabel("Impossible de se connecter au serveur",JLabel.CENTER);
            info.setFont(info.getFont().deriveFont(32f));
            info.setForeground(Color.BLACK);
            info.setBorder(new CompoundBorder(info.getBorder(), new EmptyBorder(0,0,20,0)));
            panBackground.add(info,BorderLayout.SOUTH);
            JButton bouton_quitter = new JButton("Quitter");
            bouton_quitter.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    infoFrame.dispose();
                }
            });
            bouton_quitter.setPreferredSize(new Dimension(700,50));
            infoFrame.add(bouton_quitter,BorderLayout.SOUTH);
            infoFrame.setVisible(true);
        }
        else{
            infoFrame = new JFrame("Information");
            infoFrame.setSize(700,300);
            infoFrame.setUndecorated(true);
            infoFrame.setLocationRelativeTo(null);
            infoFrame.setLayout(new BorderLayout());
            JPanel panBackground = new ImagePanel("JDialog_Serveur");
            panBackground.setLayout(new BorderLayout());
            panBackground.setPreferredSize(new Dimension(700,250));
            infoFrame.add(panBackground,BorderLayout.NORTH);
            JLabel info = new JLabel("En attente de la connexion du client",JLabel.CENTER);
            info.setFont(info.getFont().deriveFont(32f));
            info.setForeground(Color.BLACK);
            info.setBorder(new CompoundBorder(info.getBorder(), new EmptyBorder(0,0,20,0)));
            panBackground.add(info,BorderLayout.SOUTH);
            JButton bouton_quitter = new JButton("Quitter");
            bouton_quitter.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    infoFrame.dispose();
                    controler.closeSocket();
                }
            });
            bouton_quitter.setPreferredSize(new Dimension(700,50));
            infoFrame.add(bouton_quitter,BorderLayout.SOUTH);
            infoFrame.setVisible(true);
        }
    }
}
