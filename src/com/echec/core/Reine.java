package com.echec.core;

public class Reine extends Piece{

	public Reine(int x, int y,Couleur c,String name) {
		super(x, y, TypePiece.reine,c,name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean DepIsValid(int x, int y) {
		// TODO Auto-generated method stub
		
		boolean valid = false;
		
		//v�rification des coordonn�es cibles
		if(CoordIsValid(x, y)){
					
			//r�cup�ration des coordonn�es de la pi�ce
			int x_dep = this.getX();
			int y_dep = this.getY();
					
			//si le d�placement est horizontal ou vertical ou en diagonale
			if( ((x_dep-x)==0)||((y_dep-y)==0) || java.lang.Math.abs((x_dep-x)) == java.lang.Math.abs(y_dep-y) ){
						
				//le d�placement est possible
				valid= true;
			}
		}
		//le d�placement est impossible
		return valid;
	}
    @Override
    public String toString() {
        return this.getColor().toString().toUpperCase().substring(0,1)+"_Re";
    }

}
