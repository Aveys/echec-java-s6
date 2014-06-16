package com.echec.core;

public class Roi extends Piece{

	public Roi(int x, int y) {
		
		super(x, y, TypePiece.roi);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public boolean DepIsValid(int x, int y) {
		// TODO Auto-generated method stub
		
		//vérification des coordonnées cibles
		if(CoordIsValid(x, y)){
			
			//récupération des coordonnées de la pièce
			int x_dep = this.getX();
			int y_dep = this.getY();
			
			//si les coordonnées cibles sont à 1 case d'écart avec les coordonnées de départ
			if( (x >= (x_dep-1)) && (x <= (x_dep+1)) ){
				
				if((y >= (y_dep-1)) && (y <= (y_dep+1))){
					
					return true;
				}
			}
		}
		return false;
	}

}
