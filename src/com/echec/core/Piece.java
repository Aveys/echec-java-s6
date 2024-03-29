package com.echec.core;


/**
 * Created by arthurveys on 16/06/2014.
 */
public abstract class Piece {
	
	private int x;
	private int y;
	private Couleur color;
	private TypePiece type;
    public String name;


    /**
     * Build a piece
     * @param x X coodinate of the piece
     * @param y Y coordinate of the piece
     * @param type the type of the piece
     * @param c the color of the piece
     */
	public Piece(int x, int y, TypePiece type,Couleur c,String name) {
		if(CoordIsValid(x, y)){
			this.x = x;
			this.y = y;
			this.type = type;
            this.color=c;
            this.name=name;
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


	//teste la validit� de 2 coordonn�es
	/**
	 * 
	 * @param x coordonée x sur l'échiquier
	 * @param y coordonée y sur l'échiquier
	 * @return TRUE si la coordon�e est valide (existe sur l'�chiquier de 8x8)
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
	
	//methode abstraite v�rifiant si le d�placement est possible pour la pi�ce
	/**
	 * 
	 * @param x coordonnée d'arrivée x sur l'échiquier
	 * @param y coordonnée d'arrivée y sur l'échiquier
	 * @return TRUE si le dépalcement de la piéce vers la case x,y est possible
	 */
	public abstract boolean DepIsValid(int x, int y);

    /**
     * Display information of the piece
     * @return String of informations
     */
    @Override
    public String toString() {
        return color.toString().toUpperCase().substring(0,1)+"_"+name;
    }
}
