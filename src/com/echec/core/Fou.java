package com.echec.core;


public class Fou extends Piece{

	public Fou(int x, int y) {
		super(x, y, TypePiece.fou);
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
			
			//si le d�placement est uniquement en diagonal
			if(java.lang.Math.abs((x_dep-x)) == java.lang.Math.abs(y_dep-y)){
				
				//le d�placement est possible
				return true;
			}
		}
		//le d�placement est impossible
		return false;
	}

}
