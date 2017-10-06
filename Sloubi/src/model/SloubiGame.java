package model;

import java.util.Observer;

/**
 * @author francoise.perrin
 * 
 * Cette classe est fortement couplée à un Echiquier qu'elle crée
 * Elle le rend  Observable et en simplifie l'interface
 * (DP Proxy, Facade, Observer)
 *
 */
public class SloubiGame /*extends Observable  implements BoardGames */{

	//private Echiquier echiquier;

	/**
	 * Cree une instance de la classe Echiquier
	 * et notifie ses observateurs
	 */
	public SloubiGame() {
		super();
		/*this.echiquier = new Echiquier();
		this.notifyObservers(echiquier.getPiecesIHM()); */
	}

	public void addObserver(Observer frame) {
		// TODO Auto-generated method stub
		
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	/*@Override
	public String toString() {
		String st = "";
		st += "\n" + echiquier.getMessage() + "\n";
		st = echiquier.toString();	
		return  st;
	}*/


	/*/**
	 * Permet de deplacer une piece connaissant ses coordonnees initiales vers ses
	 * coordonnees finales si le deplacement est "legal". 
	 * Si deplacement OK, permet l'alternance des joueurs.
	 * @param xInit
	 * @param yInit
	 * @param xFinal
	 * @param yFinal
	 * @return OK si deplacement OK
	 * si OK, permet l'alternance des joueurs
	 */
	/*public boolean move (int xInit, int yInit, int xFinal, int yFinal){
		boolean ret = false; 

		ret = echiquier.isMoveOk(xInit, yInit, xFinal, yFinal);
		if (ret){
			ret = echiquier.move(xInit, yInit, xFinal, yFinal);
		}
		if (ret){
			echiquier.switchJoueur();
		}		
		
		this.notifyObservers(echiquier.getPiecesIHM()); 
		return ret;	
	}
	
	public static void main(String[] str){
		SloubiGame ch = new SloubiGame();
	}
	
	public boolean isEnd(){
		return echiquier.isEnd();		
	}

	public String getMessage() {
		return echiquier.getMessage();
	}


	public Couleur getColorCurrentPlayer(){		
		return echiquier.getColorCurrentPlayer();		
	}	

	public Couleur getPieceColor(int x, int y){
		return echiquier.getPieceColor(x, y);
	}

	

	/* (non-Javadoc)
	 * @see java.util.Observable#notifyObservers(java.lang.Object)
	 */
	/*@Override
	public void	notifyObservers(Object arg) {
		super.setChanged();
		super.notifyObservers(arg); 
	}

	/* (non-Javadoc)
	 * @see java.util.Observable#addObserver(java.util.Observer)
	 */
	/*@Override
	public void addObserver(Observer o){
		super.addObserver(o);
		this.notifyObservers(echiquier.getPiecesIHM()); 
	}*/
}
