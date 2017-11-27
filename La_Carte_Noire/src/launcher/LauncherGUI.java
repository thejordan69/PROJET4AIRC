package launcher;

import view.MenuGUI;

public class LauncherGUI {
    private static MenuGUI menu;
    
    public static void main(String[] args) {
        menu = new MenuGUI(); 
         menu.setVisible(true);
    }
}
