package com.echec.core;

public class Reine extends Piece{

	public Reine(int x, int y) {
		super(x, y, TypePiece.reine);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean DepIsValid(int x, int y) {
		// TODO Auto-generated method stub
		
		boolean valid = false;
		
		//vérification des coordonnées cibles
		if(CoordIsValid(x, y)){
					
			//récupération des coordonnées de la pièce
			int x_dep = this.getX();
			int y_dep = this.getY();
					
			//si le déplacement est horizontal ou vertical ou en diagonale
			if( ((x_dep-x)==0)||((y_dep-y)==0) || java.lang.Math.abs((x_dep-x)) == java.lang.Math.abs(y_dep-y) ){
						
				//le déplacement est possible
				valid= true;
			}
		}
		//le déplacement est impossible
		return valid;
	}

}
