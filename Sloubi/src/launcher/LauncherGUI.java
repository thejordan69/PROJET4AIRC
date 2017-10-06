package launcher;

import java.awt.Dimension;
import java.util.Observer;
import javax.swing.JFrame;
import controler.SloubiGameControler;
import controler.SloubiGameControlers;
import model.SloubiGame;
import view.PlateauGUI;

public class LauncherGUI {

	public static void main(String[] args) {

		SloubiGame sloubiGame;	
		SloubiGameControlers sloubiGameControler;
		JFrame frame;	
		Dimension dim;
	
		//Window Menu = new Window();
		dim = new Dimension(700, 700);
		sloubiGame = new SloubiGame();	
		sloubiGameControler = new SloubiGameControler(sloubiGame);
		
		frame = new PlateauGUI("Sloubi", sloubiGameControler,  dim);
		sloubiGame.addObserver((Observer) frame);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(600, 10);
		frame.pack();
		frame.setVisible(true);
	}
}
