/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Frederic
 */
//package model;

import java.io.Serializable;

public class Coord implements Serializable {

	private static final long serialVersionUID = 1L;
	public int x, y;
	
	/**
	 * @param x
	 * @param y
	 */
	public Coord(int x, int y) {
		this.x = x; 
		this.y = y;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "[x=" + x + ", y=" + y + "]";
	}
	
	/**
	 * @param x
	 * @param y
	 * @return true si les coordonnees sont valides (dans un plateau de 6*6)
	 */
	public static boolean coordonnees_valides(int x, int y){
		return ( (x<=6) && (x>=0) && (y<=6) && (y>=0) );
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coord other = (Coord) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
}
