package com.echec.core;

public class Roi extends Piece{

	public Roi(int x, int y) {
		
		super(x, y, TypePiece.roi);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public boolean DepIsValid(int x, int y) {
		// TODO Auto-generated method stub
		
		//v�rification des coordonn�es cibles
		if(CoordIsValid(x, y)){
			
			//r�cup�ration des coordonn�es de la pi�ce
			int x_dep = this.getX();
			int y_dep = this.getY();
			
			//si les coordonn�es cibles sont � 1 case d'�cart avec les coordonn�es de d�part
			if( (x >= (x_dep-1)) && (x <= (x_dep+1)) ){
				
				if((y >= (y_dep-1)) && (y <= (y_dep+1))){
					
					return true;
				}
			}
		}
		return false;
	}

}
