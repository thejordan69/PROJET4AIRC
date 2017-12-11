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
		File g=new File("");
                if(type.equals("carte")){
                    ret = g.getAbsolutePath()+"/src/images/carte-" + couleur + ".png";
                }
                else if(type.equals("pion")){
                    ret = g.getAbsolutePath()+"/src/images/pion-" + couleur + ".png";
                }
		
		return ret;		
	}
}
