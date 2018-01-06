package view;

import controler.AbstractGameControler;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import model.Coord;
import model.Couleur;

public abstract class AbstractPlateauGUI extends JFrame implements MouseListener, MouseMotionListener, Observer{
    JLayeredPane layeredPane;
    JPanel panel;
    JPanel recap, recapJoueurs, recapTitres;
    JLabel carte;
    JLabel jlMessage;
    int xAdjustment, yAdjustment, oldIndex;
    Dimension plateauSize;
    Dimension recapSize;
    boolean isBlocked = false;
    AbstractGameControler controler;
    
    public AbstractPlateauGUI(Dimension plateauSize, Dimension recapSize){
        this.setTitle("Plateau");
        this.setSize(new Dimension(plateauSize.width+recapSize.width,plateauSize.height+recapSize.height));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        this.setLayout(new BorderLayout());
        
        //sauvegarde des paramètres afin de les réutiliser en cas de deuxième partie
        this.plateauSize = plateauSize;
        this.recapSize = recapSize;
    }
    
    protected abstract void initComponents();
    
    protected void createGrid(){
        for (int i = 0; i < 36; i++) {
            JPanel square = new JPanel(new BorderLayout());
            square.setSize(100,100);
            square.setOpaque(false);
            //Ajout d'une propriété contenant l'index pour sauvegarder la position d'un élément sur le damier
            square.putClientProperty("index",i);
            panel.add(square);	
        }      
    }
    
    protected abstract void createRecap(Dimension recapSize);
    
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
    public void mouseClicked(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (carte == null) return;
	carte.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
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

    @Override
    public void update(Observable o, Object arg) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    } 
}
