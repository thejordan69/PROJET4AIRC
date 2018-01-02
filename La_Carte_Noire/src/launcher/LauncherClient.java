package launcher;

import java.awt.Dimension;
import java.io.IOException;
import view.PlateauGUISocket;

public class LauncherClient {
    
    public static void main(String[] args) throws InterruptedException, IOException {
        new PlateauGUISocket(new Dimension(800,800), new Dimension(500,800), "Marion", "192.168.1.13", "Client");
    }
}