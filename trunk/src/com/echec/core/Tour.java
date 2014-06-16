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
		
		//v�rification des coordonn�es cibles
		if(CoordIsValid(x, y)){
			
			//r�cup�ration des coordonn�es de la pi�ce
			int x_dep = this.getX();
			int y_dep = this.getY();
			
			//si le d�placement est seulement vertical ou horizontal
			if( ((x_dep-x)==0)||((y_dep-y)==0) ){
				
				//le d�placement est possible
				valid = true;
			}
		}
		
		//le d�placement est impossible
		return valid;
	}

}
