package com.echec.core;

public class Cavalier extends Piece{

	public Cavalier(int x, int y) {
		super(x, y, TypePiece.cavalier);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean DepIsValid(int x, int y) {
		// TODO Auto-generated method stub
		
		boolean valid=false;
		
		//vérification des coordonnées cibles
		if(CoordIsValid(x, y)){
							
			//récupération des coordonnées de la pièce
			int x_dep = this.getX();
			int y_dep = this.getY();
					
			//si le déplacement est de 2 cases horizontales et une verticale ou inversement
			if(  ((java.lang.Math.abs(x_dep-x)==2) && (java.lang.Math.abs(y_dep-y)==1)) || ((java.lang.Math.abs(x_dep-x)==1) && (java.lang.Math.abs(y_dep-y)==2))){
						
				//le déplacement est possible
				valid= true;
			}
		}
		//le déplacement n'est pas possible
		return valid;
	}
}
