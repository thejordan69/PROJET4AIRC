package view;

import controler.GameControler;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import java.awt.Component;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import model.AbstractCarteIHM;
import model.Coord;
import model.Couleur;
import model.EquipeIHM;
import model.Jeton;
import model.JoueurIHM;
import tools.ImagePanel;
import tools.ImageProvider;

public class PlateauGUI extends JFrame implements MouseListener, MouseMotionListener, Observer {
    private GameControler controler;
    private JLayeredPane layeredPane;
    private JPanel panel, recap, bandeau, recapEquipe, recapJoueurs, recapTitres;
    private JLabel carte, jlMessage;
    private int xAdjustment, yAdjustment, oldIndex, nbJoueurs;
    private Dimension plateauSize, recapSize;
    private HashMap<String,Integer> mapJoueurs;
    private String modeJeu;
    private boolean isBlocked = false;
    
    public PlateauGUI(Dimension plateauSize, Dimension recapSize, HashMap<String,Integer> mapJoueurs, String mode) {   
        this.setTitle("Plateau");
        this.setSize(new Dimension(plateauSize.width+recapSize.width,plateauSize.height+recapSize.height));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        this.setLayout(new BorderLayout());
        this.setVisible(true);

        //sauvegarde des paramètres afin de les réutiliser en cas de deuxième partie
        this.plateauSize = plateauSize;
        this.recapSize = recapSize;
        this.mapJoueurs = mapJoueurs;
        this.modeJeu = mode;
        this.nbJoueurs = mapJoueurs.size();

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
        
        jlMessage = new JLabel("", JLabel.CENTER);
        jlMessage.setFont(jlMessage.getFont().deriveFont(15f));
        getContentPane().add(jlMessage,BorderLayout.SOUTH);
        
        this.createGrid();
        this.controler = new GameControler(mapJoueurs,modeJeu);
        this.createRecap(recapSize);
        this.controler.addObserver((Observer) this); 
    }    
	
    //méthode qui permet de créer le damier
    private void createGrid(){
        for (int i = 0; i < 36; i++) {
            JPanel square = new JPanel(new BorderLayout());
            square.setSize(100,100);
            square.setOpaque(false);
            //Ajout d'une propriété contenant l'index pour sauvegarder la position d'un élément sur le damier
            square.putClientProperty("index",i);
            panel.add(square);	
        }     
        
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    
    //méthode qui permet de créer le menu récapitulatif
    private void createRecap(Dimension recapSize){
        int i =0;
        
        recap = new ImagePanel("recap");
        recap.setPreferredSize(recapSize);
        getContentPane().add(recap,BorderLayout.EAST);

        //si mode de jeu par équipe, on affiche le bandeau avec le score de chaque équipe
        if(controler.getGameMode().equals("equipe")){
            ArrayList<EquipeIHM> equipes = controler.getEquipesIHM();
            recapEquipe = new JPanel(new GridLayout(2,2));
            recapEquipe.setPreferredSize(new Dimension(recapSize.width,100));
            recapEquipe.setOpaque(false);
            recapEquipe.setBorder(BorderFactory.createLineBorder(Color.black,2));
            JLabel JLnomEquipe1 = new JLabel("EQUIPE 1",JLabel.CENTER);
            JLnomEquipe1.setForeground(equipes.get(0).getCouleur());
            JLnomEquipe1.setFont(JLnomEquipe1.getFont().deriveFont(25f));
            recapEquipe.add(JLnomEquipe1);
            JLabel JLnomEquipe2 = new JLabel("EQUIPE 2",JLabel.CENTER);
            JLnomEquipe2.setForeground(equipes.get(1).getCouleur());
            JLnomEquipe2.setFont(JLnomEquipe2.getFont().deriveFont(25f));
            recapEquipe.add(JLnomEquipe2);  
            
            JLabel JLscoreEquipe1 = new JLabel(equipes.get(0).getNbJetons(),JLabel.CENTER);
            JLscoreEquipe1.setForeground(Color.red);
            JLscoreEquipe1.setFont(JLscoreEquipe1.getFont().deriveFont(20f));
            recapEquipe.add(JLscoreEquipe1);
            JLabel JLscoreEquipe2 = new JLabel(equipes.get(1).getNbJetons(),JLabel.CENTER);
            JLscoreEquipe2.setForeground(Color.blue);
            JLscoreEquipe2.setFont(JLscoreEquipe2.getFont().deriveFont(20f));
            recapEquipe.add(JLscoreEquipe2);
            recap.add(recapEquipe);
        }
        
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
        if(controler.getGameMode().equals("equipe")){
            recapJoueurs.setPreferredSize(new Dimension(recapSize.width,recapSize.height-170));
        }
        else{
            recapJoueurs.setPreferredSize(new Dimension(recapSize.width,recapSize.height-62));
        }
        for(JoueurIHM joueur : joueurs){
            JPanel JPcurrent = new JPanel(new GridLayout(1,4));
            JPcurrent.setOpaque(false);
            JPanel JPpions = null;
            if(nbJoueurs<4){
                JPpions = new JPanel(new GridLayout(6,1));
            }
            else{
                JPpions = new JPanel(new GridLayout(3,2));
            }
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
            if(joueur.getEquipe() != null){
                JLscore.setForeground(joueur.getEquipe().getCouleur());
            }
            else{
                JLscore.setForeground(Color.BLACK);
            }
            JPcurrent.add(JLscore);
            JLabel JLpseudo = new JLabel(joueur.getPseudo(),JLabel.CENTER);
            JLpseudo.setFont(JLpseudo.getFont().deriveFont(20f));
            JLpseudo.setBorder(BorderFactory.createLineBorder(Color.black));
            if(joueur.getEquipe() != null){
                JLpseudo.setForeground(joueur.getEquipe().getCouleur());
            }
            else{
                JLpseudo.setForeground(Color.BLACK);
            }
            JPcurrent.add(JLpseudo);
            recapJoueurs.add(JPcurrent);
            i++;
        } 
        recap.add(recapJoueurs);
    }
                  
    @Override
    public void mousePressed(MouseEvent e) {
        if(isBlocked) return;
        
        carte = null;

        Component c =  panel.findComponentAt(e.getX(), e.getY());
        //si l'utilisateur clique sur une case vide (pas de JLabel) on stop
        if (c instanceof JPanel) {
            return;
        }
        //sinon, on récupère les coordonnées de la carte et on regarde si elle est bien de couleur noire
        else if (c instanceof JLabel){
            JPanel locationPanel = (JPanel) c.getParent();
            int index = (Integer) locationPanel.getClientProperty("index");
            Coord coords = new Coord(index%6, index/6);
            //si c'est bien la noire alors on sauvegarde l'index courant et on fait le traitement
            if (controler.getCarteCouleur(coords).equals(Couleur.noire)){                  
                oldIndex = index;
                Point parentLocation = c.getParent().getLocation();
                xAdjustment = parentLocation.x - e.getX();
                yAdjustment = parentLocation.y - e.getY();
                carte = (JLabel) c;
                carte.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
                carte.setSize(carte.getWidth(), carte.getHeight());
                layeredPane.add(carte, JLayeredPane.DRAG_LAYER);           
            }
        }  
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (carte == null) return;
	carte.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
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
            jlMessage.setText("Les coordonées sont hors limites");
            return;
        }

        //Calcul des coordonées source et destination à partir de l'index
        Coord initCoord = new Coord(oldIndex%6, oldIndex/6);
        //Calcul de l'index x,y destination
        Coord finalCoord = new Coord(movedIndex%6, movedIndex/6);
        boolean iSMoveSucceded = controler.move(initCoord, finalCoord);
        if(iSMoveSucceded){
            carte = null;
        }
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override public void update(Observable o, Object arg) {
        int index, i = 0;
        JLabel jLabelCarte;
        JPanel pan;
        ArrayList<AbstractCarteIHM> listeCartes;
        ArrayList<Jeton> jetonsJoueur;
        ArrayList<JoueurIHM> joueurs;
        JoueurIHM joueurCourant;
        ArrayList<EquipeIHM> equipes;

        //on redébloque la JFrame du joueur
        isBlocked = false;
        //récupère le message du plateau
        jlMessage.setText(controler.getMessage());
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
        
        //regénération des scores des équipes
        if(controler.getGameMode().equals("equipe")){
            equipes = controler.getEquipesIHM();
            JLabel scoreEquipe1 = (JLabel)recapEquipe.getComponent(2);
            scoreEquipe1.setText(equipes.get(0).getNbJetons());
            JLabel scoreEquipe2 = (JLabel)recapEquipe.getComponent(3);
            scoreEquipe2.setText(equipes.get(1).getNbJetons());  
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
                JLabel pion = null;
                if(nbJoueurs<4){
                    pion = new JLabel(new ImageIcon(ImageProvider.getImageFile("pion",tmp.getCouleur())),JLabel.CENTER);
                }
                else{
                    pion = new JLabel(new ImageIcon(ImageProvider.getImageFile("pion",tmp.getCouleur())),JLabel.LEFT);
                }
                JPpion.add(pion);
            }
            i++;
        }
        this.repaint();
        
        //si c'est la fin de la partie, on arrête la partie, on sauvegarde les scores et on affiche le gagnant
        if(controler.isEnd()){
            jlMessage.setText("La partie est terminée");
            controler.saveScores();
            JDialog winerFrame = new JDialog(this,"Fin de la partie",true);
            winerFrame.setSize(700,300);
            winerFrame.setUndecorated(true);
            winerFrame.setLocationRelativeTo(null);
            winerFrame.setLayout(new BorderLayout());
            JPanel panBackground = new ImagePanel("JDialog");
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
                    PlateauGUI newPlateau = new PlateauGUI(dimPlateau,dimRecap,mapJoueurs,modeJeu);
                }
            }); 
            bouton_quitter.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    SwingUtilities.getWindowAncestor(getContentPane()).dispose();
                }
            }); 
            JLabel gagnant = new JLabel("",JLabel.CENTER);
            if(controler.getGameMode().equals("equipe")){
                gagnant.setText("L'équipe gagnante est :" + controler.getGagnant());
            }
            else{
                gagnant.setText("Le gagnant est :" + controler.getGagnant());
            }
            gagnant.setFont(gagnant.getFont().deriveFont(34f));
            gagnant.setForeground(Color.BLACK);
            gagnant.setBorder(new CompoundBorder(gagnant.getBorder(), new EmptyBorder(0,0,20,0)));   
            panBackground.add(gagnant,BorderLayout.SOUTH);
            winerFrame.setVisible(true);
        }
        
        //si c'est le tour de l'IA, on lance le traitement
        if(!controler.isEnd() && controler.getGameMode().equals("IA") && controler.getJoueurCourantIHM().getPseudo().equals("IA")){
            try {
                //permet de bloquer freezer la JFrame tant que l'IA n'a pas finit son tour 
                isBlocked = true;
                controler.moveIA();
            } catch (InterruptedException ex) {
                Logger.getLogger(PlateauGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}