package com.echec.core;

public class Cavalier extends Piece{

	public Cavalier(int x, int y,Couleur c) {
		super(x, y, TypePiece.cavalier,c);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean DepIsValid(int x, int y) {
		// TODO Auto-generated method stub
		
		boolean valid=false;
		
		//v�rification des coordonn�es cibles
		if(CoordIsValid(x, y)){
							
			//r�cup�ration des coordonn�es de la pi�ce
			int x_dep = this.getX();
			int y_dep = this.getY();
					
			//si le d�placement est de 2 cases horizontales et une verticale ou inversement
			if(  ((java.lang.Math.abs(x_dep-x)==2) && (java.lang.Math.abs(y_dep-y)==1)) || ((java.lang.Math.abs(x_dep-x)==1) && (java.lang.Math.abs(y_dep-y)==2))){
						
				//le d�placement est possible
				valid= true;
			}
		}
		//le d�placement n'est pas possible
		return valid;
	}
}
