package view;

import controler.GameControler;
import java.awt.BorderLayout;
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
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.AbstractCarte;
import model.Coord;
import model.Couleur;
import tools.ImageProvider;

public class PlateauGUI extends JFrame implements MouseListener, MouseMotionListener, Observer {
        private GameControler controler;
        private JLayeredPane layeredPane;
        private JPanel panel;
        private JLabel carte;
        private int xAdjustment, yAdjustment, oldIndex;
        
	public PlateauGUI(Dimension boardSize, HashMap<String,Integer> mapJoueurs) {
            this.setTitle("Damier");
            this.setSize(boardSize);
            this.setLocationRelativeTo(null);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
            
            layeredPane = new JLayeredPane();
            getContentPane().add(layeredPane);
            layeredPane.setPreferredSize(boardSize);
            layeredPane.addMouseListener(this);
            layeredPane.addMouseMotionListener(this);
            
            panel = new JPanel();
            layeredPane.add(panel, JLayeredPane.DEFAULT_LAYER);
            panel.setLayout(new GridLayout(6,6));
            panel.setPreferredSize(boardSize);
            panel.setBounds(0, 0, boardSize.width, boardSize.height);
            this.createGrid();
            this.controler = new GameControler(mapJoueurs);
            this.controler.addObserver((Observer) this);
	}    
	
    //méthode qui permet de créer le damier
    private void createGrid(){
        for (int i = 0; i < 36; i++) {
            JPanel square = new JPanel(new BorderLayout());
            square.setSize(100,100);
            //Ajout d'une propriété contenant l'index pour sauvegarder la position d'un élément sur le damier
            square.putClientProperty("index",i);
            panel.add(square);	
        }     
        this.pack();
        this.setVisible(true);
    }
                  
    @Override
    public void mousePressed(MouseEvent e) {
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
            System.out.println("Les coordonées sont hors limites");
            update(null,null);
            return;
        }

        //Calcul des coordonées source et destination à partir de l'index
        Coord initCoord = new Coord(oldIndex%6, oldIndex/6);
        //Calcul de l'index x,y destination
        Coord finalCoord = new Coord(movedIndex%6, movedIndex/6);
        boolean iSMoveSucceded = controler.move(initCoord, finalCoord);
        if (iSMoveSucceded){
            carte = null;
            //test si c'est la fin de la partie
            if(controler.isEnd()){
                System.out.println("La partie est terminée");
            }
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
        int index;
        JLabel jLabelCarte;
        JPanel pan;

        //Efface et recrée la grille
        panel.removeAll();
        this.createGrid();
        
        ArrayList<AbstractCarte> liste = controler.getListeCartes();
        for(AbstractCarte tmp : liste){
            //si la carte n'est pas déjà gagnée
            if((tmp.getX() != -1) && (tmp.getY() != -1)){
                index = tmp.getX()+tmp.getY()*6;
                jLabelCarte = new JLabel(new ImageIcon(ImageProvider.getImageFile(tmp.getCouleur())));
                pan = (JPanel) panel.getComponent(index);
                pan.add(jLabelCarte);
            } 
        }
    }
}