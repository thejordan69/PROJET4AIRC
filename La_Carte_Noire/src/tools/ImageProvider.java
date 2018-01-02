package tools;

import java.io.File;
import model.Couleur;

public class ImageProvider {
	
    /**
     * private pour ne pas instancier d'objets
     */
    private ImageProvider() {}	

    /**
     * @param pieceType
     * @param pieceCouleur
     * @return nom fichier contenant image de la pi�ce
     */

    public static String getImageFile(String type, Couleur couleur){
        String ret = null;
        File g = new File("");
        switch (type) {
            case "carte":
                ret = g.getAbsolutePath()+"/src/images/carte-" + couleur + ".png";
                break;
            case "pion":
                ret = g.getAbsolutePath()+"/src/images/pion-" + couleur + ".png";
                break;
            case "recap":
                ret = g.getAbsolutePath()+"/src/images/Fond_Recap.png";
                break;
            case "plateau":
                ret = g.getAbsolutePath()+"/src/images/Fond_Plateau.png";
                break;
            case "menu":
                ret = g.getAbsolutePath()+"/src/images/Fond_Menu.jpg";
                break;
            case "JDialog":
                ret = g.getAbsolutePath()+"/src/images/Fond_JDialog.png";
                break;
            case "regles":
                ret = g.getAbsolutePath()+"/src/images/regles.png";
                break;
            default:
                break;
        }

        return ret;		
    }
}
