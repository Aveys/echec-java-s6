package com.echec.core;

public class Pion extends Piece{

	private int moves = 0;
	

	public void setMoves(int moves) {
		this.moves = moves;
	}

	public int getMoves() {
		return moves;
	}



	public Pion(int x, int y,Couleur c) {
		super(x, y, TypePiece.pion,c);
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
			
			//si le d�placement est en avnt et de 1 a 2 cases maxi
			if((y-y_dep<=2) && (y-y_dep>0) && (java.lang.Math.abs(x-x_dep) == 0) ){
				//le d�placement est possible
				valid= true;
			}
			//si le deplacement est d'une case en diagonale et en avant
			else if(y-y_dep==1 && (java.lang.Math.abs(x-x_dep) == 1)){
				
				//le d�placement est possible
				valid= true;
			}
		}
		//le d�placement est impossible
		return valid;
	}
	

}
