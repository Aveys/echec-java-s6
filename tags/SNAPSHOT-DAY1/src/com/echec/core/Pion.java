package com.echec.core;

public class Pion extends Piece{

	private int moves = 0;
	
	public int getMoves() {
		return moves;
	}

	public void setMoves(int moves) {
		this.moves = moves;
	}

	public Pion(int x, int y) {
		super(x, y, TypePiece.pion);
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
			
			//si le déplacement est en avnt et de 1 a 2 cases maxi
			if((y-y_dep<=2) && (y-y_dep>0) && (java.lang.Math.abs(x-x_dep) == 0) ){
				
				//le déplacement est possible
				valid= true;
			}
			//si le deplacement est d'une case en diagonale et en avant
			else if(y-y_dep==1 && (java.lang.Math.abs(x-x_dep) == 1)){
				
				//le déplacement est possible
				valid= true;
			}
		}
		//le déplacement est impossible
		return valid;
	}
	

}
