package launcher;

import java.awt.Dimension;
import java.io.IOException;
import view.PlateauGUISocket;
import view.ReseauGUI;

public class LauncherServeur {
    
    public static void main(String[] args) throws InterruptedException, IOException {
        //new PlateauGUISocket(new Dimension(800,800), new Dimension(500,800), "Damien", null, "Serveur");
        new ReseauGUI();
    }
}
