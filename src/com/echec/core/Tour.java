package com.echec.core;

public class Tour extends Piece{

	public Tour(int x, int y) {
		
		super(x, y, TypePiece.tour);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean DepIsValid(int x, int y) {
		// TODO Auto-generated method stub
		
		boolean valid =false;
		
		//vérification des coordonnées cibles
		if(CoordIsValid(x, y)){
			
			//récupération des coordonnées de la pièce
			int x_dep = this.getX();
			int y_dep = this.getY();
			
			//si le déplacement est seulement vertical ou horizontal
			if( ((x_dep-x)==0)||((y_dep-y)==0) ){
				
				//le déplacement est possible
				valid = true;
			}
		}
		
		//le déplacement est impossible
		return valid;
	}

}
