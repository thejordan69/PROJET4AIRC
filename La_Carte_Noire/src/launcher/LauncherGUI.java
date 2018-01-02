package launcher;

import java.io.IOException;
import view.MenuGUI;

public class LauncherGUI {
    private static MenuGUI menu;
    
    public static void main(String[] args) throws InterruptedException, IOException {
        menu = new MenuGUI(); 
    }
}
