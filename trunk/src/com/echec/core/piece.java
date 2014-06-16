package com.echec.core;


/**
 * Created by arthurveys on 16/06/2014.
 */
public abstract class Piece {
	
	private int x;
	private int y;
	private Couleur color;
	private TypePiece type;
	
	
	//constructeur
	public Piece(int x, int y, TypePiece type) {
		if(CoordIsValid(x, y)){
			this.x = x;
			this.y = y;
			this.type = type;
		}
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Couleur getColor() {
		return color;
	}

	public void setColor(Couleur color) {
		this.color = color;
	}

	public TypePiece getType() {
		return type;
	}

	public void setType(TypePiece type) {
		this.type = type;
	}


	//teste la validité de 2 coordonnées
	/**
	 * 
	 * @param x coordonée x sur l'échiquier
	 * @param y coordonée y sur l'échiquier
	 * @return TRUE si la coordonée est valide (existe sur l'échiquier de 8x8)
	 */
	public static boolean CoordIsValid(int x, int y){
		
		if(x>8 || x<0){
			
			return false;
		}
		if(y>8 || y<0){
			
			return false;
		}
		
		return true;
		
	}
	
	//methode abstraite vérifiant si le déplacement est possible pour la pièce
	/**
	 * 
	 * @param x coordonnée d'arrivée x sur l'échiquier
	 * @param y coordonnée d'arrivée y sur l'échiquier 
	 * @return TRUE si le dépalcement de la pièce vers la case x,y est possible 
	 */
	public abstract boolean DepIsValid(int x, int y);
}
