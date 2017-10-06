package controler;

import model.Coord;
import model.SloubiGame;

/**
 * @author francoise.perrin
 * 
 *         Ce controleur local précise comment empêcher un joueur à qui ce n'est pas le tour 
 *         de jouer, de déplacer une image de pièce sur le damier
 *
 */
public class SloubiGameControler extends AbstractSloubiGameControler {
	private SloubiGame sloubiGame;
	
	public SloubiGameControler(SloubiGame sloubiGame) {
		super(sloubiGame);
	}

	@Override
	public boolean move(Coord initCoord, Coord finalCoord) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEnd() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isPlayerOK(Coord initCoord) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see controler.AbstractChessGameControler#isPlayerOK(model.Coord)
	 * 
	 * cette méthode vérifie que la couleur de la pièce que l'utilisateur
	 * tente de déplacer est bien celle du jeu courant
	 * la vue se servira de cette information pour empêcher tout déplacement sur le damier
	 */
/*	@Override
	public boolean isPlayerOK(Coord initCoord) {
		return (this.getColorCurrentPlayer() == this.getPieceColor(initCoord));
		
	}
	
	/* (non-Javadoc)
	 * @see controler.AbstractChessGameControler#endMove(model.Coord, model.Coord, java.lang.String)
	 * 
	 * Pas d'action supplémentaire dans un contrôleur local en fin de move
	 */
	/*@Override
	protected void endMove(Coord initCoord, Coord finalCoord,
			String promotionType) {

	}*/
}
