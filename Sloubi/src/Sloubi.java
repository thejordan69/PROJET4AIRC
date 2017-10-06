/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package model;

import java.util.List;
import java.util.ArrayList;
/**
 *
 * @author Frederic
 */
public class Sloubi {

    @SuppressWarnings("unused")
    private List<Coord> mouvementsPossible;
    
    public Sloubi(Coord coord) {
		
	}
    
    
    
    private int x, y;
    
    
    /**
     * @return indice de la colonne ou est positionnee le sloubi
     */
	public int getX() {
		return x;
	}

	/**
	 * @return indice de la ligne ou est positionnee le sloubi
	 */
	public int getY() {
		return y;
	}
        
        /**
	 * @return true si piece effectivement capturee Positionne x et y à -1
	 */
	public boolean capture(){
		this.x=-1;
		this.y=-1;
		return true;
	}
        
        /**
	 * @param xFinal
	 * @param yFinal
	 * @param isCatchOk
	 * @param isCastlingPossible
	 * @return true si déplacement légal 
	 */	
	public boolean isMoveOk(int xFinal, int yFinal, boolean isCatchOk, boolean isCastlingPossible)
	{
		if ((xFinal == this.getX() && yFinal != this.getY()) || (xFinal != this.getX() && yFinal == this.getY()))
			return true;
		else
			return false;
	}
        
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
