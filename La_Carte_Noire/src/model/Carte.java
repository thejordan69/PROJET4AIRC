package model;

public interface Carte {

	/**
	 * @return la coordonnée x
	 */
	public int getX();
	
	/**
	 * @return la coordonnée y
	 */
	public int getY();
	
	/**
	 * @return la couleur de la pièce
	 */
	public Couleur getCouleur();
	

	/**
	 * @return le nom de la pièce
	 */	
	public String getName();
	
	/**
	 * Test si le mouvement est correct selon chaque type de pièce
	 */
	public boolean isMoveOk(int xFinal, int yFinal, boolean isCatchOk, boolean isCastlingPossible);
	
	/**
	 * Change la position de la pièce sans verifications
	 * @param xFinal
	 * @param yFinal
	 * @return true si changement effectué
	 */
	public boolean move(int xFinal, int yFinal);
	
	/**
	 * Useless
	 * @return -
	 */
	public boolean capture();
}
