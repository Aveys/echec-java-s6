package com.echec.core;


public class Fou extends Piece{

	public Fou(int x, int y) {
		super(x, y, TypePiece.fou);
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
			
			//si le déplacement est uniquement en diagonal
			if(java.lang.Math.abs((x_dep-x)) == java.lang.Math.abs(y_dep-y)){
				
				//le déplacement est possible
				return true;
			}
		}
		//le déplacement est impossible
		return false;
	}

}
