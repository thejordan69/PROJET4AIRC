package tools;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import model.Couleur;

public class ImageProvider {
	
	/**
	 * private pour ne pas instancier d'objets
	 */
	private ImageProvider() {

	}	
	
	/**
	 * @param pieceType
	 * @param pieceCouleur
	 * @return nom fichier contenant image de la pi�ce
	 */
	public static String getImageFile(Couleur couleur){
		String ret = null;
		File g=new File("");
		ret = g.getAbsolutePath()+"/src/images/" + couleur + ".png";
		return ret;		
	}
}
