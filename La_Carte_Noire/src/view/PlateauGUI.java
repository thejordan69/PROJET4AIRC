package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javafx.scene.layout.Border;
import model.Couleur;
import controler.GameControlers;

public class PlateauGUI extends JFrame implements MouseListener, MouseMotionListener, Observer, ActionListener {
	private GameControlers sloubiGameControler;
	private JLayeredPane layeredPane;
	private JPanel chessBoard;
	private JLabel chessPiece;
	private int xAdjustment, yAdjustment, oldIndex;
	
	public PlateauGUI(String name, GameControlers chessGameControler, Dimension boardSize) {
		this.sloubiGameControler = sloubiGameControler;
		
		//Use a Layered Pane for this this application
		layeredPane = new JLayeredPane();
		getContentPane().add(layeredPane);
		layeredPane.setPreferredSize(boardSize);
		layeredPane.addMouseListener(this);
		layeredPane.addMouseMotionListener(this);
		
		//Ajout de l'échiquier
		chessBoard = new JPanel();
		layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
		chessBoard.setLayout( new GridLayout(6, 6) );
		chessBoard.setPreferredSize( boardSize );
		chessBoard.setBounds(0, 0, boardSize.width, boardSize.height);

		
		
	    
	    //Instanciation d'un objet JPanel

	  /*  JPanel pan = new JPanel();
	    this.setTitle("Sloubi");
	    this.setSize(400, 500);
	    this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);             
	    this.setVisible(true);*/
	    
	    //Ajout d'un bouton
	    /*JButton bouton_jouer = new JButton("Jouer");
                JButton bouton_regles = new JButton("R�gles");
                JButton bouton_options = new JButton("Options");
                JButton bouton_scores = new JButton("Scores");*/

	    //D�finition de sa couleur de fond
	    //pan.setBackground(Color.red);        

	    //On pr�vient notre JFrame que notre JPanel sera son content pane
               /* this.setLayout(new GridLayout(5, 1));
                JButton bouton1 = new JButton("Jouer");
                bouton1.addActionListener(this);
                this.getContentPane().add(bouton1);
                this.getContentPane().add(new JButton("R�gles"));
                this.getContentPane().add(new JButton("Options"));
                this.getContentPane().add(new JButton("Scores"));*/
	    /*this.setContentPane(pan);
	    pan.add(bouton_jouer);
                pan.add(bouton_regles);
                pan.add(bouton_options);
                pan.add(bouton_scores);*/
              //  this.setVisible(true);
		
		//Création du damier
		this.createGrid();
	}

		    
		    
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Crée un damier vide
	 */
	private void createGrid(){
		Map<Color, Integer> Couleurs;
		
		/*Couleurs = new HashMap<>();
		Couleurs.put(Color.GREEN, 8);
		Couleurs.put(Color.BLUE, 7);
		Couleurs.put(Color.RED, 6);
		Couleurs.put(Color.PINK, 5);
		Couleurs.put(Color.YELLOW, 4);
		Couleurs.put(Color.CYAN, 3);
		Couleurs.put(Color.ORANGE, 2);*/
		
		Color couleurs[] = {Color.BLUE,Color.GREEN,Color.RED,Color.PINK,Color.YELLOW,Color.CYAN,Color.ORANGE,Color.BLACK};
		int nombres[] = {8,7,6,5,4,3,2,1};
		
		//Cr�ation du damier
		for (int i = 0; i < 36; i++) {
			JPanel square = new JPanel(new BorderLayout());
			
			//Ajout d'une propri�t� contenant l'index pour sauvegarder la position d'un �lement
			square.putClientProperty("index", i);
			chessBoard.add(square);
			
			int random = (int) (Math.random()*(8));
			
			while (nombres[random]<=0) {
				random = (int) (Math.random()*(8));
			}
			square.setBackground(couleurs[random]);
			nombres[random] = nombres[random]-1;
			square.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
			
		}
	}



	@Override
	public void actionPerformed(ActionEvent arg0) {
		
	}
	
	/*@Override
	public void update(Observable o, Object arg) {
		List<PieceIHM> piecesIHM = (List<PieceIHM>) arg;
		JLabel jLabelPiece;
		JPanel panel;
		int index;
		//Efface et recrée la grille
		if (chessBoard.getComponentCount()>0){
			chessBoard.removeAll();
			this.createGrid();
		}
		//Charge les pièces à partir d'une liste de PieceIHM
		for (PieceIHM piece : piecesIHM){
			index = piece.getX()+piece.getY()*8; //Calcul de l'index
			jLabelPiece = new JLabel(new ImageIcon(ChessImageProvider.getImageFile(piece.getNamePiece(), piece.getCouleur())));
		
			jLabelPiece.putClientProperty("index", index);
			panel = (JPanel)chessBoard.getComponent(index);
			panel.add(jLabelPiece);
		}
		//Donne des informations sur la partie dans le titre de la fenêtre
		this.setTitle(chessGameControler.getMessage());
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (chessPiece == null) return;
		chessPiece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		chessPiece = null;
		Component c =  chessBoard.findComponentAt(e.getX(), e.getY());
		
		if (c instanceof JPanel) return;
		//Sauvegarde de l'index source
		JLabel oldLocationLabel = (JLabel) c;
		
		//Récupération de l'index via la propriété ajoutée précédemment
		oldIndex = (Integer) oldLocationLabel.getClientProperty("index");
			
		Point parentLocation = c.getParent().getLocation();
		xAdjustment = parentLocation.x - e.getX();
		yAdjustment = parentLocation.y - e.getY();
		chessPiece = (JLabel)c;
		chessPiece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
		
		chessPiece.setSize(chessPiece.getWidth(), chessPiece.getHeight());
		layeredPane.add(chessPiece, JLayeredPane.DRAG_LAYER);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		int movedIndex;
		
		if(chessPiece == null) return;
		
	    chessPiece.setVisible(false);
	    Component c =  chessBoard.findComponentAt(e.getX(), e.getY());
	   
	    //Récupère l'index à l'aide des propriétés rajoutées, selon les différents cas:
	    //S'il y a une pièce à la destination:
	    if (c instanceof JLabel){
	    	JLabel newLocationLabel = (JLabel) c;
	    	movedIndex = (Integer) newLocationLabel.getClientProperty("index");
	    //Si la case de destination est vide:
	    } else {
	    	JPanel newBox = (JPanel) c;
			movedIndex = (Integer) newBox.getClientProperty("index");
	    }
	   
	    //Calcul de l'index x,y source
	    Coord initCoord = new Coord(oldIndex%8, oldIndex/8);
	    //Calcul de l'index x,y destination
	    Coord finalCoord = new Coord(movedIndex%8, movedIndex/8);
	    
	    boolean iSMoveSucceded = chessGameControler.move(initCoord, finalCoord);
	    if (chessGameControler.isPlayerOK(initCoord)){
	    	if (iSMoveSucceded){
		    	Container parent = (Container)c;
		    	chessPiece.putClientProperty("index", movedIndex);
			    parent.add( chessPiece );
			    chessPiece.setVisible(true);
			    chessPiece = null;
		    }
	    }
	    System.out.println(chessGameControler.getMessage());
	    
	}*/
}
